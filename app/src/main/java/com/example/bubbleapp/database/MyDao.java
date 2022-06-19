package com.example.bubbleapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bubbleapp.models.Chat;
import com.example.bubbleapp.models.Message;

import java.util.List;

@Dao
public interface MyDao {
    // CHATS
    @Query("SELECT * FROM chat")
    List<Chat> getAllContacts();

    @Query("SELECT * FROM chat WHERE id = :id")
    Chat getChat(int id);

    @Delete
    void deleteChat(Chat... chats);

    @Insert
    void insertChats(Chat... chats);

    // MESSAGES
    @Query("SELECT * FROM message ")
    List<Message> getAllMessages();

    @Query("SELECT * FROM message WHERE chatId = :chatId")
    List<Message> getAllMessages(int chatId);

    @Query("SELECT * FROM message WHERE messageId = :id")
    Message getMessage(String id);

    @Insert
    void insertMessages(Message... messages);
}