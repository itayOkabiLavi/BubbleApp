package com.example.bubbleapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.bubbleapp.models.Chat;
import com.example.bubbleapp.models.Message;
import com.example.bubbleapp.models.User;

import java.util.List;

@Dao
public interface MyDao {
    // CHATS
    @Query("SELECT * FROM chat WHERE id = :chatId")
    Chat getChat(int chatId);

    @Query("SELECT * FROM chat WHERE contactName = :name")
    Chat getChat(String name);

    @Query("SELECT * FROM chat")
    List<Chat> getAllContacts();

    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Query("SELECT * FROM user WHERE id=:id")
    List<User> getUser(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertChats(Chat... chats);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(User... users);

    // MESSAGES
    @Query("SELECT * FROM message ")
    List<Message> getAllMessages();

    @Query("SELECT * FROM message WHERE contactName = :chatId")
    List<Message> getAllMessages(int chatId);

    @Query("SELECT * FROM message WHERE contactName = :contactName")
    List<Message> getAllMessages(String contactName);

    @Query("SELECT * FROM message WHERE id = :id")
    Message getMessage(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMessages(Message... messages);
}