package com.example.bubbleapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.bubbleapp.api.ChatsAPI;
import com.example.bubbleapp.database.MyDatabase;
import com.example.bubbleapp.databinding.ActivityLoginFormBinding;
import com.example.bubbleapp.models.Message;
import com.example.bubbleapp.models.User;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginForm extends AppCompatActivity {
    private ActivityLoginFormBinding binding;
    private DataManager dataManager;
    Button loginBtn;
    EditText name;
    EditText password;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(LoginForm.this, instanceIdResult -> {
            String fbToken = instanceIdResult.getToken();
            MyApplication.setBfToken(fbToken);
        });
        ChatsAPI chatsAPI = new ChatsAPI();
        dataManager = new DummyDataManager(this);
        binding = ActivityLoginFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loginBtn = binding.loginBtn;
        name = binding.userName;
        password = binding.password;

        loginBtn.setOnClickListener(view -> {
            JSONObject userInfo = chatsAPI.login(name.getText().toString(), password.getText().toString(), MyApplication.bfToken);
            try {
                MyApplication.setToken(userInfo.getString("token"));
                User user = new Gson().fromJson(String.valueOf(userInfo.getJSONObject("user")), User.class);
                if (user == null) MyApplication.setUser();
                MyApplication.setUser(user);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            dataManager.login("", "");
            Intent intent = new Intent(this, ChatsActivity.class);
            startActivity(intent);
        });
    }

}