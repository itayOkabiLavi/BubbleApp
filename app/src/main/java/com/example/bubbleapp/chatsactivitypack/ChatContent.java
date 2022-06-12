package com.example.bubbleapp.chatsactivitypack;

import java.util.ArrayList;
import java.util.List;

public class ChatContent {
    private String me, contactName;
    private List<ChatMessage> messageList;

    public ChatContent(String me, String contactName, List<ChatMessage> messageList) {
        this.me = me;
        this.contactName = contactName;
        this.messageList = messageList;
    }

    public ChatContent(String me, String contactName) {
        List<ChatMessage> chatMessageList = new ArrayList<>();
        new ChatContent(me, contactName, chatMessageList);
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
