package com.example.bubbleapp;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.bubbleapp.models.Chat;
import com.example.bubbleapp.models.Message;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class FirebaseNotification extends FirebaseMessagingService {
    private DataManager dataManager;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public FirebaseNotification() {
        this.dataManager = MyApplication.dataManager;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Type mapType = new TypeToken<Map<String, String>>(){}.getType();
        String payload = remoteMessage.getNotification().getBody();
        System.out.println("got notification:\n" + payload);
        Map<String, String> r = new Gson().fromJson(payload, mapType);
        // TODO: new message vs new chat
        if (r.get("action") == null || r.get("id") == null) {
            System.out.println("no action / id");
            return;
        }
        String type = r.get("action"), sender = r.get("id");
        if (r.get("action").equals("newMessage")) {

            dataManager.FBPushNewMessage(new Message("",
                    r.get("content"),
                    sender,
                    MyApplication.user.name,
                    sender,
                    "yes"));
            MyApplication.notifyMessagesDisplay();
        } else {
            if (dataManager.getContactByName(sender) == null) {
                dataManager.FBPushNewChat(new Chat(sender, r.get("server"), ""));
                MyApplication.notifyChatDisplay();
            }
        }
        //notification(getApplicationContext(), sender, content, type);


    }


    private void notification(Context context, String sender, String content, String type) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channelID")
                .setContentTitle("notification from: " + sender)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);
        NotificationManagerCompat notificationCompat = NotificationManagerCompat.from(context);
        notificationCompat.notify(1, builder.build());
    }

}