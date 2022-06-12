package com.example.bubbleapp.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
@Entity
public class Chat {
    @PrimaryKey
    @NonNull
    public String id;
    //TODO: fix
    //public List<Message> messages;
}
