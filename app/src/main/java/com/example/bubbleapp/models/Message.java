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
    @PrimaryKey
    @NonNull
    public String id;
    public String created;
    public long creationTime;
    public String content;
    //public File formFile;
    public boolean sent;
    public String fromId;
    public String toId;
    public String chatId;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Message(@NonNull String id, String content,
                   String fromId,
                   String toId,
                   String chatId, String created) {
        this.id=id;
        this.created=created;
        this.content = content;
        this.fromId = fromId;
        this.toId = toId;
        this.chatId = chatId;
        this.creationTime = System.currentTimeMillis();
    }

    public void changeMessageId(String id) { this.id = id; }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Message() {
        new Message("","Empty",
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

    @NonNull
    public String getMessageId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getChatId() {
        return chatId;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDateTime getCreationTime() {
        return Instant.ofEpochMilli(creationTime)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    @NonNull
    @Override
    public String toString() {
        return "Message{" +
                "MessageId='" + id + '\'' +
                ", created='" + creationTime + '\'' +
                ", content='" + content + '\'' +
                ", sent=" + sent +
                ", fromId='" + fromId + '\'' +
                ", toId='" + toId + '\'' +
                ", chatId='" + chatId + '\'' +
                '}';
    }
}
