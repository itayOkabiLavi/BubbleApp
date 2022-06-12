package com.example.bubbleapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.bubbleapp.models.Chat;
import com.example.bubbleapp.models.Message;
import com.example.bubbleapp.models.User;

@Database(entities = {User.class, Chat.class, Message.class}, version = 1)
@TypeConverters({FileTypeConvertor.class})
public abstract class MyDatabase extends RoomDatabase {

    public abstract MyDao MyDao();
}


