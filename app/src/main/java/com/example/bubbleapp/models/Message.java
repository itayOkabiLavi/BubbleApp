package com.example.bubbleapp.models;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
public class Message {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    public int id;
    // TODO: delete created
    public String created;
    public long creationTime;
    public String content;
    //public File formFile;
    public boolean sent;
    public String fromId;
    public String toId;
    public String contactName;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Message(String content,
                   String fromId,
                   String toId,
                   String contactName, String created) {
        this.created=created;
        this.content = content;
        this.fromId = fromId;
        this.toId = toId;
        this.contactName = contactName;
        this.creationTime = System.currentTimeMillis();
    }

    public void changeMessageId(int id) { this.id = id; }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Message() {
        new Message("Empty",
                "from no one",
                "to nobody",
                "-1","");
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public int getMessageId() { return id; }

    public String getContent() {
        return content;
    }

    public String getContactName() {
        return contactName;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDateTime getCreationTime() {
        return Instant.ofEpochMilli(creationTime)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
