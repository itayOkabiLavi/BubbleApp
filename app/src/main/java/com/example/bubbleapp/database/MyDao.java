package com.example.bubbleapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bubbleapp.models.User;

import java.util.List;

@Dao
public interface MyDao {
    /*@Query("")
    List<User> getAll();

    @Query("")
    List<User> loadAllByIds(int[] userIds);

    @Query("")
    User findByName(String first, String last);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);*/
}