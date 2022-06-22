package com.example.bubbleapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.bubbleapp.api.ChatsAPI;
import com.example.bubbleapp.databinding.ActivityRegisterBinding;
import com.example.bubbleapp.models.User;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterForm extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private DataManager dataManager;

    Button regBtn;
    EditText fullName;
    EditText nickName;
    EditText password;
    EditText validatePassword;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            setTheme(R.style.DarkTheme);
        else
            setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(RegisterForm.this, instanceIdResult -> {
            String fbToken = instanceIdResult.getToken();
            MyApplication.setFbToken(fbToken);
        });
        ChatsAPI chatsAPI = new ChatsAPI();
        dataManager = new DummyDataManager(this);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        regBtn = binding.registerBtn;
        fullName = binding.fullName;
        nickName = binding.nickName;
        password = binding.password;
        validatePassword = binding.validatePassword;


        regBtn.setOnClickListener(view -> {
            JSONObject userInfo = chatsAPI.register(fullName.getText().toString(), nickName.getText().toString(), password.getText().toString(),MyApplication.fbToken);
            try {
                MyApplication.setToken(userInfo.getString("token"));
                User user = new Gson().fromJson(String.valueOf(userInfo.getJSONObject("user")), User.class);
                if (user == null) MyApplication.setUser();
                MyApplication.setUser(user);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            dataManager.setRelevantCache();
            Intent intent = new Intent(this, ChatsActivity.class);
            startActivity(intent);
        });
    }
}
