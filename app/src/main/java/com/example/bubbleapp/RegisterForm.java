package com.example.bubbleapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import com.example.bubbleapp.api.ChatsAPI;
import com.example.bubbleapp.databinding.ActivityRegisterBinding;
import com.example.bubbleapp.models.User;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class RegisterForm extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private DataManager dataManager;

    Button regBtn;
    EditText fullName;
    EditText nickName;
    EditText password;
    EditText validatePassword;
    ImageView imageView;
    File file;
    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                    int a = 1;
                } else {
                    int a = 1;
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            });

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
        imageView = binding.viewImage;
        imageView.setImageResource(R.drawable.generic_profile_image);


        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        assert data != null;
                        Uri selectedImage = data.getData();
                        String[] filePath = {MediaStore.Images.Media.DATA};
                        Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                        c.moveToFirst();
                        int columnIndex = c.getColumnIndex(filePath[0]);
                        String picturePath = c.getString(columnIndex);
                        c.close();
                        this.file = new File(picturePath);
                        Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                        imageView.setImageBitmap(thumbnail);
                    }
                });
        imageView.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(
                    MyApplication.context, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_GRANTED) {
                int a = 1;

            } else if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                int a = 1;

            } else {

                requestPermissionLauncher.launch(
                        Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            someActivityResultLauncher.launch(intent);
        });
        regBtn.setOnClickListener(view -> {
            JSONObject userInfo = null;
            try {
                userInfo = chatsAPI.register(fullName.getText().toString(), nickName.getText().toString(), password.getText().toString(), MyApplication.fbToken, this.file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                assert userInfo != null;
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
