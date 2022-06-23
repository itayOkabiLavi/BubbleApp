package com.example.bubbleapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.bubbleapp.chatsactivitypack.LiveMessages;
import com.example.bubbleapp.models.Chat;
import com.example.bubbleapp.models.Message;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
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
        //String payload = remoteMessage.getNotification().getBody();
        //System.out.println("got notification:\n" + payload);
        Map<String, String> r = remoteMessage.getData(); //new Gson().fromJson(payload, mapType);
        // TODO: new message vs new chat
        if (r.get("action") == null || r.get("id") == null) {
            System.out.println("no action / id");
            return;
        }
        String type = r.get("action"), sender = r.get("id"), content;
        if (r.get("action").equals("newMessage")) {
            content = r.get("content");
            String msgId = sender + "," + MyApplication.user.id + "," + MyApplication.user.server + "," + LocalDateTime.now().toString() + "," + content;
            Message newMessage = new Message(msgId,
                    content,
                    sender,
                    MyApplication.user.id,
                    sender,
                    LocalDateTime.now().toString());
            dataManager.FBPushNewMessage(newMessage);
            MyApplication.notifyChatDisplay(newMessage);
        } else {
            System.out.println("chat exists? : " + dataManager.getContactByName(sender));
            if (dataManager.getContactByName(sender) == null) {
                content = r.get("server");
                dataManager.FBPushNewChat(new Chat(sender, content, ""));

            }
            content = "Already-exists-contact tries to reconnect: " + sender;
        }
        LiveMessages liveMessages = new LiveMessages();
        liveMessages.setMessages(r.get("content"));
        createNotificationChannel();
        throwNotification(getApplicationContext(), sender, content, type);
    }


    private void createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "channelID", "channelName", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("channleDescription");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    private void throwNotification(Context context, String sender, String content, String type) {
        String title;
        if (type.equals("newChat"))
            title = sender + " wants to chat with you!";
        else
            title = sender + " sent you a message.";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channelID")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationCompat = NotificationManagerCompat.from(MyApplication.context);
        notificationCompat.notify(1, builder.build());
    }


}