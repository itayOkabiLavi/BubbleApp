package com.example.bubbleapp.chatsactivitypack;

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
import com.example.bubbleapp.models.Chat;
import com.example.bubbleapp.models.Message;

import java.util.List;

public class ChatDisplayActivity extends AppCompatActivity {
    private ActivityChatDisplayBinding binding;
    private String token;
    private DataManager dataManager;
    private ChatContent chatContent;
    private String chatId;
    private List<Message> messageList;
    private MessageAdapter messageAdapter;
    private Chat chat;

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
        this.chat = dataManager.getContact(token, ChatsActivity.myName);
        // Set buttons behaviour
        ImageButton backBtn = binding.chatBackBtn;
        backBtn.setOnClickListener(view -> {
            finish();
        });

        ImageButton sendBtn = binding.chatSendBtn;

        sendBtn.setOnClickListener(view -> {
            dataManager.sendMessage(
                    token,
                    binding.chatInputText.getText().toString(),
                    "mom",
                    chatId
            );
            messageList.clear();
            messageList.addAll(dataManager.getAllMessages(chatId));
            messageAdapter.notifyDataSetChanged();
        });

        // set chats list
        dataManager.setMessagesList(chatId);
        this.messageList = dataManager.getAllMessages(chatId);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        messageAdapter = new MessageAdapter(this.messageList);
        binding.messagesRv.setAdapter(messageAdapter);
        binding.messagesRv.setLayoutManager(llm);
    }

}