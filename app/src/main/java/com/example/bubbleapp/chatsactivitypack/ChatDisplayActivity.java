package com.example.bubbleapp.chatsactivitypack;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bubbleapp.R;
import com.example.bubbleapp.databinding.ActivityChatDisplayBinding;

import java.util.ArrayList;
import java.util.List;

public class ChatDisplayActivity extends AppCompatActivity {
    private ActivityChatDisplayBinding binding;
    private ChatContent chatContent;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_display);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            chatContent = (ChatContent) extras.getSerializable("chaContent");
        } else {
            List<ChatMessage> messageList = new ArrayList<>();
            messageList.add(new ChatMessage(ChatMessage.ME, "Empty", "empty message from me"));
            messageList.add(new ChatMessage("Empty", ChatMessage.ME, "empty message to me"));
            chatContent = new ChatContent("ME", "Empty", messageList);
        }

    }
}