package com.example.bubbleapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bubbleapp.databinding.ActivityChatsBinding;

public class Chats extends AppCompatActivity {
    private ActivityChatsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
    }
}