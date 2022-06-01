package com.example.bubbleapp;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.bubbleapp.chatsactivitypack.ChatPreviewInfo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DummyDataManager implements DataManager {

    public DummyDataManager() { }

    @Override
    public String login(String name, String password) {
        String token = "dummytoken";
        return token;
    }

    @Override
    public String register(String name, String nickname, String password) {
        String token = "dummytoken";
        return token;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public List<ChatPreviewInfo> getContacts(String token) {
        List<ChatPreviewInfo> list = new ArrayList<>();
        list.add(
                new ChatPreviewInfo(
                        "Itay",
                        "hey there",
                        LocalDateTime.now(),
                        "pic",
                        "123")
        );
        list.add(
                new ChatPreviewInfo(
                        "Nadav",
                        "hey there!!",
                        LocalDateTime.now(),
                        "pic",
                        "123")
        );
        return list;
    }

    @Override
    public boolean sendMessage(String token, String destination, String textMessage) {

        return true;
    }

    @Override
    public void getMessage() {

    }


}
