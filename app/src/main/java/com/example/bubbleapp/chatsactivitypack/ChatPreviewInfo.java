package com.example.bubbleapp.chatsactivitypack;

import com.example.bubbleapp.models.Chat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ChatPreviewInfo implements Serializable {
    private Chat chat;
    private String lastMessage;
    private LocalDateTime lastMessageDate;
    // ALL THE VIEW PARTS ARE DONE IN "CHAT ADAPTER"
    public ChatPreviewInfo(Chat chat) {
        this.lastMessage = lastMessage;
        this.lastMessageDate = lastMessageDate;
        lastMessageDate = LocalDateTime.now();
        this.chat = chat;
    }

    public String getContactName() {
        return chat.getContactName();
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public LocalDateTime getLastMessageDate() {
        return lastMessageDate;
    }

    public String getProfilePicture() {
        return getProfilePicture();
    }

    public Chat getChat() {
        return chat;
    }

}
