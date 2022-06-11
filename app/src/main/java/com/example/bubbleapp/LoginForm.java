package com.example.bubbleapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bubbleapp.api.ChatsAPI;
import com.example.bubbleapp.api.WebServiceAPI;
import com.example.bubbleapp.databinding.ActivityLoginFormBinding;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginForm extends AppCompatActivity {
    private ActivityLoginFormBinding binding;
    Button loginBtn;
    EditText name;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        ChatsAPI chatsAPI = new ChatsAPI();
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            binding = ActivityLoginFormBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            loginBtn = binding.loginBtn;
            name = binding.userName;
            password = binding.password;
            loginBtn.setOnClickListener(view -> {
                chatsAPI.login(name.getText().toString(), password.getText().toString());
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            });
        }
    }

}