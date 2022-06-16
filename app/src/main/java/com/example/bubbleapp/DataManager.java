package com.example.bubbleapp;

import com.example.bubbleapp.chatsactivitypack.ChatPreviewInfo;
import com.example.bubbleapp.models.Chat;
import com.example.bubbleapp.models.Message;

import java.util.List;

public interface DataManager {

    public void clearCache();

    public String login(String name, String password);

    public String register(String name, String nickname, String password) ;
    // Contacts
    public List<ChatPreviewInfo> getContacts(String token);

    public void addContact(Chat chat);

    // Messages
    public List<Message> getAllMessages();
    public List<Message> getAllMessages(int chatId);

    public boolean sendMessage(String token, Message message);
    public boolean sendMessage(String token, String content, String to, int chatId);

    public Message getMessage(String id);
}
