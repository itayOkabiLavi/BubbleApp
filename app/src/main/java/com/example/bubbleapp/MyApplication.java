package com.example.bubbleapp;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.bubbleapp.models.Message;
import com.example.bubbleapp.models.User;

public class MyApplication extends Application {
    public static Context context;
    public static String token;
    public static String fbToken;
    public static User user;
    public static DataManager dataManager;
    public static NotifiableActivity chatsDisplay = null;
    public static NotifiableActivity messagesDisplay = null;

    public static void setUser(User user) {
        MyApplication.user = user;
    }

    public static void setUser() {
        MyApplication.user = new User(
                "userId",
                "userName",
                "userServer",
                "userLastMessage",
                "userLastType",
                "userLastDate");
    }

    public static void setFbToken(String fbToken) {
        MyApplication.fbToken = fbToken;
    }

    public static void setBfToken() {
        MyApplication.fbToken = "dummyBfToken";
    }

    public static void setToken(String token) {
        MyApplication.token = token;
    }

    public static void setToken() {
        MyApplication.token = "dummyToken";
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        MyApplication.dataManager = new DummyDataManager(context);
    }

    public static void notifyChatDisplay(Message newMessage) {
        if (chatsDisplay != null) chatsDisplay.public_notify(newMessage);
        else System.out.println("chatsDisplay is null");
    }

}
