package com.example.bubbleapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.bubbleapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private DataManager dataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // dataManager can be received as constructor argument
        dataManager = new DummyDataManager();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.registerBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterForm.class);
            startActivity(intent);
        });
        binding.mainLoginBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, Chats.class);
            String token = dataManager.login("itay", "itay123");
            intent.putExtra("token", token);
            startActivity(intent);
        });
    }
}