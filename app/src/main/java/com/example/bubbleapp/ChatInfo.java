package com.example.bubbleapp;

import java.util.Date;

public class ChatInfo {
    private String id;
    private String server;
    private String contactName;
    private String lastMessage;
    private Date lastMessageDate;
    private String profilePicture;

    public ChatInfo(String contactName, String lastMessage, Date lastMessageDate, String profilePicture, String server) {
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

    public Date getLastMessageDate() {
        return lastMessageDate;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getId() {
        return id;
    }
}
