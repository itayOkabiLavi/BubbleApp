package com.example.bubbleapp.models;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Message {
    @PrimaryKey
    @NonNull
    public String id;
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
    public Message(@NonNull String id,
                   String content,
                   String fromId,
                   String toId,
                   String contactName, String created) {

        this.created = created;//generateCreationTime(created);
        this.content = content;
        this.fromId = fromId;
        this.toId = toId;
        this.contactName = contactName;

        this.id = id;//generateNowId(id);
        //this.creationTime = System.currentTimeMillis();
    }

    public void changeMessageId(String id) { this.id = id; }

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

    public String getMessageId() { return id; }

    public String getContent() {
        return content;
    }

    public String getContactName() {
        return contactName;
    }

    public String generateNowId(String id) {
        if (id.length() < 1)
            return fromId + "," + toId + "," + created + "," + content;
        return id;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String generateCreationTime(String createdTime) {
        if (!createdTime.equals("NOW")) return createdTime;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss.S");
        return LocalDateTime.now().format(dateFormat)
                + "T"
                + LocalDateTime.now().format(timeFormat);
    }

    public String parseCreationTime() {
        String[] date = this.created.split("T");
        String time = date[1].split("\\.")[0].substring(0, 5);
        String dateTime = date[0] +" "+ time;
        return dateTime;
        //String[] result = {time, dateTime};
        //return result;
    }
}
