package com.example.bubbleapp.chatsactivitypack;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bubbleapp.DataManager;
import com.example.bubbleapp.DummyDataManager;
import com.example.bubbleapp.MyApplication;
import com.example.bubbleapp.NotifiableActivity;
import com.example.bubbleapp.R;
import com.example.bubbleapp.databinding.ActivityChatDisplayBinding;
import com.example.bubbleapp.models.Chat;
import com.example.bubbleapp.models.Message;

import java.util.List;

public class ChatDisplayActivity extends NotifiableActivity {
    private ActivityChatDisplayBinding binding;
    //private String token;
    private String server;
    private DataManager dataManager;
    private String chatAddressee;
    private String chatId;
    private List<Message> messageList;
    private MessageAdapter messageAdapter;
    private Chat chat;
    private LiveMessages liveMessages;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            setTheme(R.style.DarkTheme);
        else
            setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        liveMessages=new ViewModelProvider((this)).get(LiveMessages.class);
        final Observer<String> messageObserver = newName -> {
            // Update the UI, in this case, a TextView.
            messageList.clear();
            messageList.addAll(dataManager.getAllMessages(chatId));
            messageAdapter.notifyDataSetChanged();
            binding.chatInputText.setText("");
        };
        liveMessages.getCurrentMessages().observe(this, messageObserver);

        // View
        this.binding = ActivityChatDisplayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Data manager
        this.dataManager = new DummyDataManager(this.getApplicationContext());
        // Extract data received
        Bundle extras = getIntent().getExtras();
        //this.token = extras.getString("token");
        this.server = extras.getString("server");
        this.chatId = extras.getString("chatId");
        this.chatAddressee = extras.getString("chatAddressee");
        // Set buttons behaviour
        ImageButton backBtn = binding.chatBackBtn;
        backBtn.setOnClickListener(view -> {
            finish();
        });
        binding.chatContactNickname.setText(chatAddressee);
        binding.chatSendBtn.setOnClickListener(view -> {
            if (binding.chatInputText.getText().toString().equals("")) return;
            dataManager.sendMessage(
                    MyApplication.token,
                    binding.chatInputText.getText().toString(),
                    chatAddressee,
                    server,
                    chatId
            );
            messageList.clear();
            messageList.addAll(dataManager.getAllMessages(chatId));
            messageAdapter.notifyDataSetChanged();
            binding.chatInputText.setText("");
        });
        // set chats list
        this.messageList = dataManager.getAllMessages(chatId);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        messageAdapter = new MessageAdapter(this.messageList);
        binding.messagesRv.setAdapter(messageAdapter);
        binding.messagesRv.setLayoutManager(llm);

        MyApplication.messagesDisplay = this;
        MyApplication.context = this;
    }

    private void refresh() {
        messageList.clear();
        messageList.addAll(dataManager.getAllMessages(chatId));
        messageAdapter.notifyDataSetChanged();
    }
}