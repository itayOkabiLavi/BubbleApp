package com.example.bubbleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.bubbleapp.api.ChatsAPI;
import com.example.bubbleapp.databinding.ActivityLoginFormBinding;
import com.example.bubbleapp.databinding.ActivityRegisterBinding;

import org.json.JSONException;
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
            JSONObject userInfo = chatsAPI.register(fullName.getText().toString(), nickName.getText().toString(), password.getText().toString());
            Intent intent = new Intent(this, ChatsActivity.class);
            try {
                JSONObject user = userInfo.getJSONObject("user");
                intent.putExtra("token", userInfo.getString("token"));
                intent.putExtra("id", user.getString("id"));
                intent.putExtra("name", user.getString("name"));
                intent.putExtra("server", user.getString("server"));
                intent.putExtra("last", user.getString("last"));
                intent.putExtra("lastType", user.getString("lastType"));
                try {
                    intent.putExtra("lastDate", user.getJSONObject("lastDate").toString());
                } catch (Exception ignored) {
                }
                intent.putExtra("userMessages", user.getJSONArray("userMessages").toString());
                intent.putExtra("profileImg", user.getJSONObject("profileImg").toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            startActivity(intent);
        });
    }
}
