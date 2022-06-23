package com.example.bubbleapp.chatsactivitypack;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.bubbleapp.models.Chat;

import java.io.Serializable;

public class ChatPreviewInfo implements Serializable {
    public Chat chat;
    public String lastMessage;
    public String lastMessageDate;
    // ALL THE VIEW PARTS ARE DONE IN "CHAT ADAPTER"
    @RequiresApi(api = Build.VERSION_CODES.O)
    public ChatPreviewInfo(Chat chat) {
        lastMessageDate = "";
        lastMessage = "Start chatting with " + chat.contactName;
        this.chat = chat;
    }

    public String getContactName() {
        return chat.getContactName();
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getLastMessageDate() {
        return lastMessageDate;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void setLastMessageDate(String lastMessageDate) {
        this.lastMessageDate = lastMessageDate;
    }

    public String getProfilePicture() {
        return getProfilePicture();
    }

    public Chat getChat() {
        return chat;
    }

}
