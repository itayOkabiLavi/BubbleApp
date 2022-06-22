package com.example.bubbleapp.chatsactivitypack;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.bubbleapp.MyApplication;
import com.example.bubbleapp.api.ChatsAPI;
import com.example.bubbleapp.database.MyDao;
import com.example.bubbleapp.database.MyDatabase;
import com.example.bubbleapp.models.Message;

import java.util.ArrayList;
import java.util.List;

public class MessagesRepository {
    private String contactId;
    private MyDao myDao;
    private MessagesListData messagesListData;
    private ChatsAPI chatsAPI;

    public MessagesRepository() {
        myDao = Room.databaseBuilder(
                MyApplication.context,
                MyDatabase.class,
                "myDatabase").allowMainThreadQueries().build().MyDao();
        messagesListData = new MessagesListData();
        //this.contactId = contactId;
        this.chatsAPI=new ChatsAPI(messagesListData,myDao);
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public MutableLiveData<List<Message>> getAll() {
        return messagesListData;
    }

    public void add(Message message) {
        myDao.insertMessages(message);
        chatsAPI.sendMessage(message);
    }

    public void reload() {
        chatsAPI.getMessages(contactId);
    }

    public class MessagesListData extends MutableLiveData<List<Message>> {
        public MessagesListData() {
            super();
            setValue(new ArrayList<Message>());
        }

        @Override
        protected void onActive() {
            super.onActive();
            new Thread(() -> {
                postValue(myDao.getAllMessages(contactId));
            }).start();
        }
    }
}
