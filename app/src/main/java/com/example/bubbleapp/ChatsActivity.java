package com.example.bubbleapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bubbleapp.chatsactivitypack.ChatPreviewInfo;
import com.example.bubbleapp.chatsactivitypack.ChatPreviewInfoAdapter;
import com.example.bubbleapp.databinding.ActivityChatsBinding;
import com.example.bubbleapp.models.Chat;

import java.util.List;

public class ChatsActivity extends AppCompatActivity {
    private ActivityChatsBinding binding;
    private DataManager dataManager;
    private String token;
    public static String myName;
    private List<ChatPreviewInfo> chatPreviewInfoList;
    private ChatPreviewInfoAdapter chatPreviewInfoAdapter;
    private List<String> chatTitles;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set binding
        this.binding = ActivityChatsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras = getIntent().getExtras();
        // set token and user-name
        this.token = extras.getString("token");
        this.myName = extras.getString("myName");
        binding.chatsUsername.setText(myName);

        // set dataManager
        this.dataManager = new DummyDataManager(this.getApplicationContext());
        //dataManager.clearCache();

        // set chats list - may be in login
        this.chatPreviewInfoList = dataManager.getContacts(token);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        chatPreviewInfoAdapter = new ChatPreviewInfoAdapter(this);
        binding.chatsRv.setAdapter(chatPreviewInfoAdapter);
        binding.chatsRv.setLayoutManager(llm);

        // set behaviour
        binding.chatsAddContact.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Title");

            final EditText input = new EditText(this);
            builder.setView(input);

            builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String name = input.getText().toString();
                    dataManager.addContact(new Chat(name, "dummy", "dummyImg"));
                    chatPreviewInfoList.clear();
                    chatPreviewInfoList.addAll(dataManager.getContacts(token));
                    chatPreviewInfoAdapter.notifyDataSetChanged();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        });
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