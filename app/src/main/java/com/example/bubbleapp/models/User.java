package com.example.bubbleapp.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
//@TypeConverters({FileTypeConvertor.class})
public class User {
    @PrimaryKey
    @NonNull
    public String id;
    public String name;
    public String server;
    public String last;
    public String lastType;
    public String lastDate;

    public User(@NonNull String id, String name, String server, String last, String lastType, String lastDate) {
        this.id = id;
        this.name = name;
        this.server = server;
        this.last = last;
        this.lastType = lastType;
        this.lastDate = lastDate;
    }

    public User() {
    }

    //TODO: fix
    //public File profileImg;

}
