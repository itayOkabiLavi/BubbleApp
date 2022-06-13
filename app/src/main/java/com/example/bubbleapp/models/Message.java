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
    public String messageId;
    public long creationTime;
    public String content;
    //public File formFile;
    public boolean sent;
    public String fromId;
    public String toId;
    public String chatId;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Message(String content,
                   String fromId,
                   String toId,
                   String chatId,
                   int id) {
        this.content = content;
        this.fromId = fromId;
        this.toId = toId;
        this.chatId = chatId;
        this.messageId = Integer.toString(id);
        this.creationTime = System.currentTimeMillis();
    }

    public void setMessageId(String id) { this.messageId = id; }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Message() {
        new Message("Empty",
                "from no one",
                "to nobody",
                "a>b",
                -1);
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
        return messageId;
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

    public String generateId(String from, String to, int serialNumber) {
        return from + ">" + to + "#" + serialNumber;
    }

    @Override
    public String toString() {
        return "Message{" +
                "MessageId='" + messageId + '\'' +
                ", created='" + creationTime + '\'' +
                ", content='" + content + '\'' +
                ", sent=" + sent +
                ", fromId='" + fromId + '\'' +
                ", toId='" + toId + '\'' +
                ", chatId='" + chatId + '\'' +
                '}';
    }
}
