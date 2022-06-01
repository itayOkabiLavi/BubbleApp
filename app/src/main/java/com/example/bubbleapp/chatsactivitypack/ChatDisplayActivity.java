package com.example.bubbleapp.chatsactivitypack;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bubbleapp.R;
import com.example.bubbleapp.databinding.ActivityChatDisplayBinding;

import java.time.LocalDateTime;

public class ChatDisplayActivity extends AppCompatActivity {
    private ActivityChatDisplayBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_display);

        ChatPreviewInfo chatPreviewInfo;
        Bundle extras = getIntent().getExtras();
        if (extras == null) {

        } else {
            chatPreviewInfo = new ChatPreviewInfo(
                    "Empty",
                    "None",
                    LocalDateTime.now(),
                    "asd",
                    "123");
        }
    }
}