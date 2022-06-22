package com.example.bubbleapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.bubbleapp.api.ChatsAPI;
import com.example.bubbleapp.database.MyDao;
import com.example.bubbleapp.database.MyDatabase;
import com.example.bubbleapp.models.Chat;
import com.example.bubbleapp.models.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatsRepository {
    private MyDao myDao;
    private ChatsListData chatsListData;
    private ChatsAPI chatsAPI;

    public ChatsRepository() {
        myDao = Room.databaseBuilder(
                MyApplication.context,
                MyDatabase.class,
                "myDatabase").allowMainThreadQueries().build().MyDao();
        chatsListData = new ChatsListData();
        this.chatsAPI=new ChatsAPI(chatsListData,myDao);
    }

    public LiveData<List<Chat>> getAll() {
        return chatsListData;
    }

    public void add(Chat chat) {
        chatsAPI.addContact(chat);
    }

    public void reload() {
        chatsAPI.getContacts();
    }

    public class ChatsListData extends MutableLiveData<List<Chat>> {
        public ChatsListData() {
            super();
            setValue(new ArrayList<Chat>());
        }

        @Override
        protected void onActive() {
            super.onActive();
            new Thread(() -> {
                postValue(myDao.getAllContacts());
            }).start();
        }
    }
}
