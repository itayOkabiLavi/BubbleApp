package com.example.bubbleapp.chatsactivitypack;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bubbleapp.api.ChatsAPI;
import com.example.bubbleapp.models.Message;

import java.util.List;

public class MessagesViewModel extends ViewModel {
    private MessagesRepository messagesRepository;
    private MutableLiveData<List<Message>> messages;
    private String contactId;
    public MessagesViewModel() {
        messagesRepository = new MessagesRepository();
        messages = messagesRepository.getAll();
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
        messagesRepository.setContactId(contactId);
        messages = messagesRepository.getAll();
    }

    public LiveData<List<Message>> getMessages() {
        return messages;
    }

    public void add(Message message) {
        messages.getValue().add(message);
        messagesRepository.add(message);
    }
    public void reload(){
        messagesRepository.reload();
    }
}
