package com.example.bubbleapp.database;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class NotificationHandler extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        notificationChannel();
    }

    private void notificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "channelID", "channelName", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("channleDescription");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
