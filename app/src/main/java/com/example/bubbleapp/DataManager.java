package com.example.bubbleapp;

import com.example.bubbleapp.chatsactivitypack.ChatContent;
import com.example.bubbleapp.chatsactivitypack.ChatMessage;
import com.example.bubbleapp.chatsactivitypack.ChatPreviewInfo;

import java.util.List;

public interface DataManager {

    public String login(String name, String password);

    public String register(String name, String nickname, String password) ;

    public List<ChatPreviewInfo> getContacts(String token);

    public ChatContent getContact(String token, String id);

    public boolean sendMessage(String token, String destination, String textMessage);

    public ChatMessage getMessage();
}
