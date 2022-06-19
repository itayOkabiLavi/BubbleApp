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
        lastMessageDate = null;
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

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void setLastMessageDate(LocalDateTime lastMessageDate) {
        this.lastMessageDate = lastMessageDate;
    }

    public String getProfilePicture() {
        return getProfilePicture();
    }

    public Chat getChat() {
        return chat;
    }

}
