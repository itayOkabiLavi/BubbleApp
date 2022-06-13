package com.example.bubbleapp.chatsactivitypack;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.bubbleapp.models.Message;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ChatMessage implements Serializable {
    private String from, to, text, chatId;
    private LocalDateTime time;
    static final String ME = "ME";
    @RequiresApi(api = Build.VERSION_CODES.O)
    // Use ChatMessage.ME const when needed
    public ChatMessage(String from, String to, String text) {
        this.from = from;
        this.to = to;
        this.text = text;
        this.time = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "ChatMessage={" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", text='" + text + '\'' +
                ", time=" + time +
                '}';
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Message getMessage() {
        return new Message(this.text, this.from, this.to, this.chatId, 0);
    }
}
