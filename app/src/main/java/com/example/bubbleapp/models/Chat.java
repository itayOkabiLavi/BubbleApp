package com.example.bubbleapp.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.bubbleapp.R;

@Entity
public class Chat {

    @PrimaryKey
    @NonNull
    public String contactName;
    public String server;
    public String image;

    public Chat(@NonNull String contactName, String server, String image) {
        this.contactName = contactName;
        this.server = server;
        this.image = image;
    }

    @NonNull
    public String getContactName() {
        return contactName;
    }

    public String getServer() {
        return server;
    }

    public String getImage() {
        return image;
    }
}
