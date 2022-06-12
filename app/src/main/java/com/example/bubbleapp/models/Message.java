package com.example.bubbleapp.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.File;
@Entity
public class Message {
    @PrimaryKey
    @NonNull
    public String MessageId;
    public String created;
    public String content;
    //public File formFile;
    public boolean sent;
    public String fromId;
    public String toId;
    public String chatId;
}
