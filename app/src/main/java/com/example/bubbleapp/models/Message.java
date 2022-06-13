package com.example.bubbleapp.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {
    @PrimaryKey
    @NonNull
    public String messageId;
    public String createdAt;
    public String content;
    //public File formFile;
    public boolean sent;
    public String fromId;
    public String toId;
    public String chatId;

    public Message(String content,
                   String fromId,
                   String toId,
                   String chatId,
                   int serialNumberInChat) {
        this.content = content;
        this.fromId = fromId;
        this.toId = toId;
        this.chatId = chatId;
        this.messageId = generateId(fromId, toId, serialNumberInChat);
    }

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

    public String getCreatedAt() {
        return createdAt;
    }

    public String generateId(String from, String to, int serialNumber) {
        return from + ">" + to + "#" + serialNumber;
    }

    @Override
    public String toString() {
        return "Message{" +
                "MessageId='" + messageId + '\'' +
                ", created='" + createdAt + '\'' +
                ", content='" + content + '\'' +
                ", sent=" + sent +
                ", fromId='" + fromId + '\'' +
                ", toId='" + toId + '\'' +
                ", chatId='" + chatId + '\'' +
                '}';
    }
}
