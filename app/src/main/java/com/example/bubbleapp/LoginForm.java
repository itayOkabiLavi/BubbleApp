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

import org.json.JSONException;
import org.json.JSONObject;

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
        ChatsAPI chatsAPI = new ChatsAPI();

        binding = ActivityLoginFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loginBtn = binding.loginBtn;
        name = binding.userName;
        password = binding.password;

        loginBtn.setOnClickListener(view -> {
            JSONObject userInfo = chatsAPI.login(name.getText().toString(), password.getText().toString());
            Intent intent = new Intent(this, ChatsActivity.class);
            try {
                JSONObject user = userInfo.getJSONObject("user");
                intent.putExtra("token",userInfo.getString("token"));
                intent.putExtra("id",user.getString("id"));
                intent.putExtra("name",user.getString("name"));
                intent.putExtra("server",user.getString("server"));
                intent.putExtra("last",user.getString("last"));
                intent.putExtra("lastType",user.getString("lastType"));
                try {
                    intent.putExtra("lastDate",user.getJSONObject("lastDate").toString());
                } catch (Exception ignored) {}
                intent.putExtra("userMessages",user.getJSONArray("userMessages").toString());
                intent.putExtra("profileImg",user.getJSONObject("profileImg").toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            startActivity(intent);
        });
    }

}