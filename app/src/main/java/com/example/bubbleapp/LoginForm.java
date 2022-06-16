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
import com.google.firebase.iid.FirebaseInstanceId;

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
        dataManager=new DummyDataManager(this);
        binding = ActivityLoginFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loginBtn = binding.loginBtn;
        name = binding.userName;
        password = binding.password;

        loginBtn.setOnClickListener(view -> {
            JSONObject userInfo = chatsAPI.login(name.getText().toString(), password.getText().toString(),MyApplication.bfToken);
            try {
                MyApplication.setToken(userInfo.getString("token"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            dataManager.login(name.getText().toString(), password.getText().toString());
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
                MyDatabase db = Room.databaseBuilder(getApplicationContext(),
                        MyDatabase.class, "myDatabase").build();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            startActivity(intent);
        });
    }

}