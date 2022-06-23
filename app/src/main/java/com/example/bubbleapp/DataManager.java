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
    public List<ChatPreviewInfo> getContacts(String token, List<ChatPreviewInfo> old);
    public Chat getContactByName(String name);
    public void addContact(Chat chat);
    public void FBPushNewChat(Chat... chats);
    public ChatPreviewInfo lastMessageUpdate(String chatId, Message message);

    // Messages
    public List<Message> getAllMessages();
    public List<Message> getAllMessages(String chatId);

    public boolean sendMessage(String token, Message message);
    public boolean sendMessage(String token, String content, String to,String server, String chatId);
    public void FBPushNewMessage(Message... messages);
    public Message getMessage(String id);
}
