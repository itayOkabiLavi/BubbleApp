package com.example.bubbleapp.database;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.io.File;

public class FileTypeConvertor {
    @TypeConverter
    public static String fromFile(File file) {
        return file.toString();
    }

}
