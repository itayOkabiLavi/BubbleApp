package com.example.bubbleapp.chatsactivitypack;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bubbleapp.models.Message;

import java.util.List;
import java.util.Objects;

public class LiveMessages extends ViewModel {

    private static MutableLiveData<String> messages;

    public MutableLiveData<String> getCurrentMessages() {
        if (messages == null) {
            messages = new MutableLiveData<String>();
        }
        return messages;
    }

    public void setMessages(String messages) {
        LiveMessages.messages.postValue(messages);
    }
}