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


    public Chat(String participant1, String participant2) {
        this.participant1 = participant1;
        this.participant2 = participant2;
        id += participant1 + "&" + participant2 + "&";
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


    //TODO: fix
    //public List<Message> messages;
}
