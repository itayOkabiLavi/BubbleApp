package com.example.bubbleapp;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    public static Context context;
    public static String token;

    public static void setToken(String token) {
        MyApplication.token = token;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        context = getApplicationContext();
    }
}
