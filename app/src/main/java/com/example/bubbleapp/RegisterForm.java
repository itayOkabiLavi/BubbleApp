package com.example.bubbleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.bubbleapp.api.ChatsAPI;
import com.example.bubbleapp.databinding.ActivityLoginFormBinding;
import com.example.bubbleapp.databinding.ActivityRegisterBinding;

import org.json.JSONObject;

public class RegisterForm extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    Button regBtn;
    EditText fullName;
    EditText nickName;
    EditText password;
    EditText validatePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChatsAPI chatsAPI = new ChatsAPI();
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        regBtn = binding.registerBtn;
        fullName = binding.fullName;
        nickName = binding.nickName;
        password = binding.password;
        validatePassword = binding.validatePassword;
        regBtn.setOnClickListener(view -> {
            JSONObject token = chatsAPI.register(fullName.getText().toString(), nickName.getText().toString(), password.getText().toString(), validatePassword.getText().toString());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}
