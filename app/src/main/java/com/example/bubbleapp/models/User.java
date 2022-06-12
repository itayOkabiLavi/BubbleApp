package com.example.bubbleapp.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.File;
import java.util.List;
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
    //TODO: fix
    //public File profileImg;

}
