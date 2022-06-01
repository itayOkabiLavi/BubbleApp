package com.example.bubbleapp.chatsactivitypack;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ChatPreviewInfo implements Serializable {
    private String id;
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
        this.id = contactName + "^" + server;
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

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ChatInfo = {" +
                "id='" + id + '\'' +
                ", server='" + server + '\'' +
                ", contactName='" + contactName + '\'' +
                ", lastMessage='" + lastMessage + '\'' +
                ", lastMessageDate=" + lastMessageDate +
                '}';
    }
}
