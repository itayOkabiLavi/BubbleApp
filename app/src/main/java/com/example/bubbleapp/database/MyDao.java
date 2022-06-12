package com.example.bubbleapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bubbleapp.models.Message;
import com.example.bubbleapp.models.User;

import java.util.List;

@Dao
public interface MyDao {
    @Query("SELECT * FROM user")
    List<User> getAllUsers();
/*
    @Query("")
    List<User> loadAllByIds(int[] userIds);
*/
    @Query("SELECT * FROM user WHERE id = :id")
    User getUserById(String id);

    @Query("SELECT * FROM user WHERE name = :name")
    User getUserByName(String name);

    @Insert
    void insertUsers(User... users);

    @Insert
    void insertMessage(Message... messages);
    @Delete
    void deleteUsers(User user);


}