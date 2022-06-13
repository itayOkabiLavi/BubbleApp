package com.example.bubbleapp.chatsactivitypack;

import com.example.bubbleapp.ChatsActivity;
import com.example.bubbleapp.models.Chat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ChatPreviewInfo implements Serializable {
    private Chat chat;
    private String server;
    private String contactName;
    private String lastMessage;
    private LocalDateTime lastMessageDate;
    private String profilePicture;
    // ALL THE VIEW PARTS ARE DONE IN "CHAT ADAPTER"
    public ChatPreviewInfo(String contactName, String lastMessage, LocalDateTime lastMessageDate, String profilePicture, String server) {
        this.contactName = contactName;
        this.lastMessage = lastMessage;
        this.lastMessageDate = lastMessageDate;
        this.profilePicture = profilePicture;
        this.server = server;
        this.chat = new Chat(contactName, ChatsActivity.myName);
    }

    public String getContactName() {
        return contactName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public LocalDateTime getLastMessageDate() {
        return lastMessageDate;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public Chat getChat() {
        return chat;
    }

    @Override
    public String toString() {
        return "ChatPreviewInfo{" +
                "chat=" + chat +
                ", server='" + server + '\'' +
                ", contactName='" + contactName + '\'' +
                ", lastMessage='" + lastMessage + '\'' +
                ", lastMessageDate=" + lastMessageDate +
                ", profilePicture='" + profilePicture + '\'' +
                '}';
    }
}
