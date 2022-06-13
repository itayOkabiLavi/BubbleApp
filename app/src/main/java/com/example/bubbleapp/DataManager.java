package com.example.bubbleapp;

import com.example.bubbleapp.chatsactivitypack.ChatMessage;
import com.example.bubbleapp.chatsactivitypack.ChatPreviewInfo;
import com.example.bubbleapp.models.Chat;
import com.example.bubbleapp.models.Message;

import java.util.List;

public interface DataManager {

    public String login(String name, String password);

    public String register(String name, String nickname, String password) ;

    public List<ChatPreviewInfo> getContacts(String token);

    public Chat getContact(String token, String id);

    // Messages
    public List<ChatMessage> getAllMessages(String chatId);

    public boolean sendMessage(String token, Message message);

    public ChatMessage getMessage();
}
