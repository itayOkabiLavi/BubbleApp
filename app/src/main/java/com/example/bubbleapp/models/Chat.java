package com.example.bubbleapp.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Chat {
    @PrimaryKey
    @NonNull
    public String id;
    public String participant1, participant2;
    public int lastMessageId;


    public Chat(String participant1, String participant2) {
        this.participant1 = participant1;
        this.participant2 = participant2;
        id += participant1 + "&" + participant2 + "&";
        lastMessageId = 0;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getParticipant1() {
        return participant1;
    }

    public String getParticipant2() {
        return participant2;
    }

    public int notifyMessageAdded() {
        int temp = this.lastMessageId;
        lastMessageId += 1;
        return temp;
    }

    //TODO: fix
    //public List<Message> messages;
}
