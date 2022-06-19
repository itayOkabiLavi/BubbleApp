package com.example.bubbleapp;

import android.app.Application;
import android.content.Context;

import com.example.bubbleapp.models.User;

public class MyApplication extends Application {
    public static Context context;
    public static String token;
    public static String bfToken;
    public static User user;

    public static void setUser(User user) {
        MyApplication.user = user;
    }

    public static void setBfToken(String bfToken) {
        MyApplication.bfToken = bfToken;
    }


    public static void setToken(String token) {
        MyApplication.token = token;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        context = getApplicationContext();
    }
}
