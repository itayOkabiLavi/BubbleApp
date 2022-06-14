package com.example.bubbleapp;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Room;

import com.example.bubbleapp.api.ChatsAPI;
import com.example.bubbleapp.chatsactivitypack.ChatPreviewInfo;
import com.example.bubbleapp.database.MyDao;
import com.example.bubbleapp.database.MyDatabase;
import com.example.bubbleapp.models.Chat;
import com.example.bubbleapp.models.Message;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// NO singleton
public class DummyDataManager extends Activity implements DataManager {
    private List<ChatPreviewInfo> dummyChats;
    private MyDatabase myDatabase;
    private MyDao myDao;
    private ChatsAPI chatsAPI;
    // TODO: add firebase
    // TODO: add relevant methods for firebase


    @RequiresApi(api = Build.VERSION_CODES.O)
    public DummyDataManager(Context applicationContext) {
        chatsAPI = new ChatsAPI();
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
    }

    @Override
    public void clearCache() {

        this.myDatabase.clearAllTables();
    }

    @Override
    public String login(String name, String password) {
        // TODO: send login to server
        String token = "dummytoken";
        setRelevantCache();
        return token;
    }

    @Override
    public String register(String name, String nickname, String password) {
        // TODO: send register to server
        String token = "dummytoken";
        setRelevantCache();
        return token;
    }

    private void setRelevantCache() {
         updateChats(MyApplication.token);
        // clear cache
        // update chats
        // update messages
    }

    private void updateChats(String token) {
        JSONArray jo =chatsAPI.getContacts(token);
        //myDao.insertUsers();
        // TODO: get contacts of current user from server
    }

    private void updateMessage(String token, String chatId) {
        // TODO: get messages of chat from server
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
    public List<Message> getAllMessages(String chatId) {
        return myDao.getAllMessages(chatId);
    }

    @Override
    public boolean sendMessage(String token, Message message) {
        // TODO: send message to server
        myDao.insertMessages(message);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean sendMessage(String token, String content, String to, String chatId) {
        return sendMessage(token,
                new Message(content, ChatsActivity.myName, to, chatId)
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Message getMessage(String id) {
        return new Message("content", "from", "to", "no chat");
    }

    public int getMessageId() {
        int temp = ChatsActivity.nextMessageIndex;
        ChatsActivity.nextMessageIndex += 1;
        return temp;
    }
}
