package com.example.bubbleapp;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Room;

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
    private List<Message> dummyMessages;
    private MyDatabase myDatabase;
    private MyDao myDao;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public DummyDataManager(Context applicationContext) {
        myDatabase = Room.databaseBuilder(
                applicationContext,
                MyDatabase.class,
                "myDatabase").allowMainThreadQueries().build();
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
                new Message("hi mom", "ME", "mom", "?", -1)
        );
        dummyMessages.add(
                new Message("hi itay", "ME", "mom", "?", -1)
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
    public void setMessagesList(List<Message> messageList) {
        this.dummyMessages = messageList;
    }

    @Override
    public void setMessagesList(String chatId) {
        this.dummyMessages = myDao.getAllMessages(chatId);
    }

    @Override
    public List<Message> getAllMessages(String chatId) {
        return this.dummyMessages;
    }

    @Override
    public boolean sendMessage(String token, Message message) {
        myDao.insertMessages(message);
        return true;
    }

    @Override
    public Message getMessage(String id) {
        return new Message("content", "from", "to", "no chat", -1);
    }

}
