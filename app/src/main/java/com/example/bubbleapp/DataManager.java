package com.example.bubbleapp;

import com.example.bubbleapp.chatsactivitypack.ChatPreviewInfo;
import com.example.bubbleapp.models.Chat;
import com.example.bubbleapp.models.Message;

import java.util.List;

public interface DataManager {

    public void clearCache();

    // Contacts
    List<ChatPreviewInfo> getContacts(String token);

    Chat getContactByName(String name);

    void addContact(Chat chat);

    void FBPushNewChat(Chat... chats);

    ChatPreviewInfo lastMessageUpdate(String chatId, Message message);

    // Messages
    List<Message> getAllMessages();

    List<Message> getAllMessages(String chatId);

    void setRelevantCache();

    boolean sendMessage(String token, Message message);

    boolean sendMessage(String token, String content, String to, String server, String chatId);

    void FBPushNewMessage(Message... messages);

    Message getMessage(String id);
}
