package com.example.bubbleapp.chatsactivitypack;

import java.util.List;

public class ChatContent {
    private String me, contactName;
    private List<ChatMessage> messageList;

    public ChatContent(String me, String contactName, List<ChatMessage> messageList) {
        this.me = me;
        this.contactName = contactName;
        this.messageList = messageList;
    }


    public String getMe() {
        return me;
    }

    public String getContactName() {
        return contactName;
    }

    public List<ChatMessage> getMessageList() {
        return messageList;
    }
}
