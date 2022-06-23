package com.example.bubbleapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.bubbleapp.api.ChatsAPI;
import com.example.bubbleapp.databinding.ActivityLoginFormBinding;
import com.example.bubbleapp.models.User;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class LoginForm extends AppCompatActivity {
    private ActivityLoginFormBinding binding;
    private DataManager dataManager;
    private AlertDialog alertDialog;
    Button loginBtn;
    EditText name;
    EditText password;
    Button registerButton;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            setTheme(R.style.DarkTheme);
        else
            setTheme(R.style.AppTheme);


        super.onCreate(savedInstanceState);
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(LoginForm.this, instanceIdResult -> {
            String fbToken = instanceIdResult.getToken();
            MyApplication.setFbToken(fbToken);
        });
        ChatsAPI chatsAPI = new ChatsAPI();
        dataManager = new DummyDataManager(this);
        binding = ActivityLoginFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loginBtn = binding.loginBtn;
        name = binding.userName;
        password = binding.password;
        binding.loginLogo.setColorFilter(Color.WHITE);
        registerButton = binding.loginRegisterBtn;
        SharedPreferences userDetails = MyApplication.context.getSharedPreferences("userdata", 0);
        if (!Objects.equals(userDetails.getString("token", ""), "")) {
            MyApplication.setToken(userDetails.getString("token", ""));
            User user = new Gson().fromJson(userDetails.getString("user", ""), User.class);
            if (user == null) {
                JSONObject userJO = chatsAPI.getUser(MyApplication.token);
                user = new Gson().fromJson(userJO.toString(), User.class);
            }
            if (user != null) {
                MyApplication.setUser(user);
                dataManager.setRelevantCache();
                Intent intent = new Intent(this, ChatsActivity.class);
                startActivity(intent);
            }
        }

        loginBtn.setOnClickListener(view -> {
            if (name.getText().toString().equals("tester")) {
                dummyChats();
                return;
            }
            JSONObject userInfo = chatsAPI.login(name.getText().toString(), password.getText().toString(), MyApplication.fbToken);
            try {
                SharedPreferences.Editor edit = userDetails.edit();
                edit.putString("token", userInfo.getString("token"));
                edit.putString("user", userInfo.getJSONObject("user").toString());
                edit.apply();
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


        registerButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterForm.class);
            startActivity(intent);
        });
        binding.loginSettingsBtn.setOnClickListener(view -> {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.settings_layout, null);

            dialogBuilder.setView(dialogView);
            Button apply = (Button) dialogView.findViewById(R.id.settings_apply_btn);
            Switch darkModeSwitch = (Switch) dialogView.findViewById(R.id.settings_darkmode_switch);
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
                darkModeSwitch.setChecked(true);
            else
                darkModeSwitch.setChecked(false);
            EditText serverInput = (EditText) dialogView.findViewById(R.id.settings_server_input);
            serverInput.setText(MyApplication.user.server);
            /*EditText fbtInput = (EditText) dialogView.findViewById(R.id.settings_fbtoken_input);
            fbtInput.setText(MyApplication.fbToken);*/
            apply.setOnClickListener(view1 -> {
                if (darkModeSwitch.isChecked())
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                MyApplication.user.server = serverInput.getText().toString();
                //MyApplication.fbToken = fbtInput.getText().toString();
                alertDialog.dismiss();
                reset();
            });

            alertDialog = dialogBuilder.create();

            alertDialog.show();
        });
    }

    private void reset() {
        Intent intent = new Intent(getApplicationContext(), LoginForm.class);

        startActivity(intent);
        finish();
    }

    private void dummyChats() {

        Intent intent = new Intent(this, ChatsActivity.class);
        String token = "token";
        MyApplication.setUser();
        MyApplication.setToken();
        intent.putExtra("token", token);
        intent.putExtra("myName", "myName");
        startActivity(intent);

    }
}