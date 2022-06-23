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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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


    public void setRelevantCache() {
        clearCache();
        updateChats(MyApplication.token);

    }

    private void updateChats(String token) {
        JSONArray jsonArray = chatsAPI.getContacts(token);
        if (jsonArray == null) {
            //myDatabase.clearAllTables();
            return;
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                String istring=jsonArray.getString(i);
                User user = new Gson().fromJson(istring, User.class);
                String img =new JSONObject(istring).getJSONObject("profileImg").getString("data");
                Chat chat = new Chat(user.name, user.server,img);
                myDao.insertChats(chat);
                myDao.insertUsers(user);
            } catch (JSONException ignored) {}
        }
        List<User> users = myDao.getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            jsonArray = chatsAPI.getMessages(token, users.get(i).name);
            for (int j = 0; j < jsonArray.length(); j++) {
                try {
                    Message message = new Gson().fromJson(jsonArray.getString(j), Message.class);
                    message.contactName = users.get(i).name;
                    myDao.insertMessages(message);
                } catch (JSONException ignored) {}
            }
        }
        // TODO: get contacts of current user from server
    }

    private void updateMessage(String token, String contactId) {
        JSONArray jsonArray = chatsAPI.getMessages(token, contactId);

        // TODO: get messages of chat from server
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public List<ChatPreviewInfo> getContacts(String token, List<ChatPreviewInfo> old) {
        List<Chat> chats = myDao.getAllContacts();
        List<ChatPreviewInfo> chatPreviewInfoList = new ArrayList<>();
        for (Chat c : chats) {
            ChatPreviewInfo chatPreviewInfo = new ChatPreviewInfo(c);
            for (ChatPreviewInfo chatPreviewInfo1 : old) {
                if (chatPreviewInfo1.chat.getContactName().equals(c.contactName)) {
                    chatPreviewInfo = chatPreviewInfo1;
                    break;
                }
            }
            chatPreviewInfoList.add(chatPreviewInfo);
        }
        old.clear();
        return chatPreviewInfoList;
    }

    @Override
    public Chat getContactByName(String name) {
        return myDao.getChat(name);
    }

    @Override
    public void addContact(Chat chat) {
        chatsAPI.addContact(chat);
        this.myDao.insertChats(chat);
    }

    @Override
    public void FBPushNewChat(Chat... chats) {
        this.myDao.insertChats(chats);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public ChatPreviewInfo lastMessageUpdate(String chatId, Message message) {
        Chat chat = myDao.getChat(chatId);
        ChatPreviewInfo chatPreviewInfo = new ChatPreviewInfo(chat);
        chatPreviewInfo.setLastMessage(message.getContent());
        chatPreviewInfo.setLastMessageDate(message.parseCreationTime());
        return chatPreviewInfo;
    }

    @Override
    public List<Message> getAllMessages(String chatId) {
        return myDao.getAllMessages(chatId);
    }


    public List<Message> getAllMessages() {
        return myDao.getAllMessages();
    }

    @Override
    public boolean sendMessage(String token, Message message) {
        // TODO: send message to server
        chatsAPI.sendMessage(message);
        myDao.insertMessages(message);
        // last message a+update
        MyApplication.notifyChatDisplay(message);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean sendMessage(String token, String content, String to, String server, String chatId) {
        String time = LocalDateTime.now().toString();
        String msgId = MyApplication.user.id + "," + to + "," + server + "," + time + "," + content;
        return sendMessage(token,
                new Message(msgId, content, MyApplication.user.name, to, chatId, time)
        );
    }

    @Override
    public void FBPushNewMessage(Message... messages) {
        this.myDao.insertMessages(messages);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Message getMessage(String id) {
        return null;
    }
}
