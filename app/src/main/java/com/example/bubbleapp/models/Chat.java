package com.example.bubbleapp.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Chat {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    @NonNull
    public String contactName;
    public String server;
    public String image;

    public Chat(@NonNull String contactName, String server, String image) {
        this.contactName = contactName;
        this.server = server;
        this.image = image;
    }

    public int getId() {
        return id;
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
