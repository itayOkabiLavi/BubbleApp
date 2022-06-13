package com.example.bubbleapp.chatsactivitypack;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bubbleapp.ChatsActivity;
import com.example.bubbleapp.DataManager;
import com.example.bubbleapp.DummyDataManager;
import com.example.bubbleapp.databinding.ActivityChatDisplayBinding;

import java.util.List;

public class ChatDisplayActivity extends AppCompatActivity {
    private ActivityChatDisplayBinding binding;
    private String token;
    private DataManager dataManager;
    private ChatContent chatContent;
    private String chatId;
    private List<ChatMessage> messageList;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // View
        this.binding = ActivityChatDisplayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Data manager
        this.dataManager = new DummyDataManager(this.getApplicationContext());
        // Extract data received
        Bundle extras = getIntent().getExtras();
        this.token = extras.getString("token");
        this.chatId = extras.getString("chatId");

        // Set buttons behaviour
        ImageButton backBtn = binding.chatBackBtn;
        backBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, ChatsActivity.class);
            startActivity(intent);
        });

        ImageButton sendBtn = binding.chatSendBtn;
        sendBtn.setOnClickListener(view -> {

        });

        // set chats list
        this.messageList = dataManager.getAllMessages(chatId);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        ChatMessageAdapter chatMessageAdapter = new ChatMessageAdapter(this.messageList);
        binding.chatMessagesRv.setAdapter(chatMessageAdapter);
        binding.chatMessagesRv.setLayoutManager(llm);
    }


}