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
import com.example.bubbleapp.models.User;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        JSONArray jsonArray = chatsAPI.getContacts(token);
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                myDao.insertUsers(new Gson().fromJson(jsonArray.getString(i), User.class));
            } catch (JSONException e) {
                break;
            }
        }
        List<User> users = myDao.getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            jsonArray = chatsAPI.getMessages(token, users.get(i).id);
            for (int j = 0; j < jsonArray.length(); j++) {
                try {
                    myDao.insertMessages(new Gson().fromJson(jsonArray.getString(j), Message.class));
                } catch (JSONException e) {
                    break;
                }
            }
        }
        // TODO: get contacts of current user from server
    }

    private void updateMessage(String token, String contactId) {
        JSONArray jsonArray = chatsAPI.getMessages(token, contactId);

        // TODO: get messages of chat from server
    }

    @Override
    public List<ChatPreviewInfo> getContacts(String token) {
        List<Chat> chats = myDao.getAllContacts();
        List<ChatPreviewInfo> chatPreviewInfoList = new ArrayList<>();
        for (Chat c : chats) chatPreviewInfoList.add(new ChatPreviewInfo(c));
        return chatPreviewInfoList;
    }

    @Override
    public void addContact(Chat chat) {
        this.myDao.insertChats(chat);
    }



    @Override
    public List<Message> getAllMessages(int chatId) {
        return myDao.getAllMessages(chatId);
    }


    public List<Message> getAllMessages() {
        return myDao.getAllMessages();
    }

    @Override
    public boolean sendMessage(String token, Message message) {
        // TODO: send message to server
        myDao.insertMessages(message);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean sendMessage(String token, String content, String to, int chatId) {
        return sendMessage(token,
                new Message(content, ChatsActivity.myName, to, chatId)
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Message getMessage(String id) {
        return null;
    }
}
