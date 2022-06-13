package com.example.bubbleapp;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bubbleapp.chatsactivitypack.ChatPreviewInfoAdapter;
import com.example.bubbleapp.chatsactivitypack.ChatPreviewInfo;
import com.example.bubbleapp.databinding.ActivityChatsBinding;

import java.util.List;

public class ChatsActivity extends AppCompatActivity {
    private ActivityChatsBinding binding;
    private DataManager dataManager;
    private String token;
    private List<ChatPreviewInfo> chatPreviewInfoList;
    private List<String> chatTitles;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("ready...");
        super.onCreate(savedInstanceState);
        // set binding
        this.binding = ActivityChatsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // set dataManager
        this.dataManager = new DummyDataManager(this.getApplicationContext());
        Bundle extras = getIntent().getExtras();
        // set token
        if (extras != null) {
            this.token = extras.getString("token");
        } else {
            this.token = "";
        }
        // set chats list - may be in login
        this.chatPreviewInfoList = dataManager.getContacts(token);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        ChatPreviewInfoAdapter chatPreviewInfoAdapter = new ChatPreviewInfoAdapter(this);
        binding.chatsRv.setAdapter(chatPreviewInfoAdapter);
        binding.chatsRv.setLayoutManager(llm);
    }

    public ActivityChatsBinding getBinding() {
        return binding;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public String getToken() {
        return token;
    }

    public List<ChatPreviewInfo> getChatPreviewInfoList() {
        return chatPreviewInfoList;
    }

    public List<String> getChatTitles() {
        return chatTitles;
    }
}