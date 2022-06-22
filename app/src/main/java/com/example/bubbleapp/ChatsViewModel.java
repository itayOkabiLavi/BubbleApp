package com.example.bubbleapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bubbleapp.models.Chat;
import com.example.bubbleapp.models.Message;

import java.util.List;

public class ChatsViewModel extends ViewModel {
    private ChatsRepository chatsRepository;
    private LiveData<List<Chat>> chats;

    public ChatsViewModel() {
        chatsRepository = new ChatsRepository();
        chats = chatsRepository.getAll();
    }

    public LiveData<List<Chat>> getChats() {
        return chats;
    }

    public void add(Chat chat) {
        chatsRepository.add(chat);
    }

    public void reload() {
        chatsRepository.reload();
    }
}
