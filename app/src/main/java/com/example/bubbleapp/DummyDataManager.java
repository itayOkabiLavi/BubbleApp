package com.example.bubbleapp;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Room;

import com.example.bubbleapp.chatsactivitypack.ChatMessage;
import com.example.bubbleapp.chatsactivitypack.ChatPreviewInfo;
import com.example.bubbleapp.database.MyDao;
import com.example.bubbleapp.database.MyDatabase;
import com.example.bubbleapp.models.Chat;
import com.example.bubbleapp.models.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class DummyDataManager extends Activity implements DataManager {
    private List<ChatPreviewInfo> dummyChats;
    private List<ChatMessage> dummyMessages;
    private MyDatabase myDatabase;
    private MyDao myDao;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public DummyDataManager(Context applicationContext) {
        myDatabase = Room.databaseBuilder(
                applicationContext,
                MyDatabase.class,
                "myDatabase").build();
        myDao = myDatabase.MyDao();
        this.dummyChats = new ArrayList<>();
        dummyChats.add(
                new ChatPreviewInfo(
                        "Itay",
                        "hey there",
                        LocalDateTime.now(),
                        "pic",
                        "123")
        );
        dummyChats.add(
                new ChatPreviewInfo(
                        "Nadav",
                        "hey there!!",
                        LocalDateTime.now(),
                        "pic",
                        "123")
        );
        dummyMessages = new ArrayList<>();
        dummyMessages.add(
                new ChatMessage("ME", "mom", "hi1")
        );
        dummyMessages.add(
                new ChatMessage("ME", "mom", "bi")
        );
    }

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

    @Override
    public List<ChatPreviewInfo> getContacts(String token) {
        return dummyChats;
    }

    @Override
    public Chat getContact(String token, String id) {
        return new Chat("itay", "mom");
    }

    @Override
    public List<ChatMessage> getAllMessages(String chatId) {
        return this.dummyMessages;
    }

    @Override
    public boolean sendMessage(String token, Message message) {
        myDao.insertMessage(message);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public ChatMessage getMessage() {
        return new ChatMessage("fromMe", "to", "stuff");
    }
}
