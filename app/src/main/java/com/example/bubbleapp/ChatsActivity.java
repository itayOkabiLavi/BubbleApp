package com.example.bubbleapp;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bubbleapp.chatsactivitypack.ChatPreviewInfo;
import com.example.bubbleapp.chatsactivitypack.ChatPreviewInfoAdapter;
import com.example.bubbleapp.chatsactivitypack.LiveMessages;
import com.example.bubbleapp.databinding.ActivityChatsBinding;
import com.example.bubbleapp.models.Chat;
import com.example.bubbleapp.models.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatsActivity extends NotifiableActivity {
    private ActivityChatsBinding binding;
    private DataManager dataManager;
    private List<ChatPreviewInfo> chatPreviewInfoList;
    private ChatPreviewInfoAdapter chatPreviewInfoAdapter;
    private List<String> chatTitles;
    private AlertDialog alertDialog;
    private ChatPreviewInfo lastChatPreviewInfo;
    private LiveMessages liveMessages;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            setTheme(R.style.DarkTheme);
        else
            setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        // set binding
        this.binding = ActivityChatsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        liveMessages=new ViewModelProvider((this)).get(LiveMessages.class);
        final Observer<String> messageObserver = newName -> {
            // Update the UI, in this case, a TextView.
            chatPreviewInfoList.clear();
            chatPreviewInfoList.addAll(dataManager.getContacts(MyApplication.token));
            chatPreviewInfoAdapter.notifyDataSetChanged();
        };
        liveMessages.getCurrentMessages().observe(this, messageObserver);
        Bundle extras = getIntent().getExtras();
        // set token and user-name
        //MyApplication.setUser();
        //MyApplication.setToken();
        // set dataManager
        this.dataManager = new DummyDataManager(this.getApplicationContext());
        //dataManager.clearCache();

        // set title
        binding.chatsUsername.setText("Hello " + MyApplication.user.name);
        // set chats list - may be in login
        chatPreviewInfoList = new ArrayList<>();
        this.chatPreviewInfoList = dataManager.getContacts(MyApplication.token);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        chatPreviewInfoAdapter = new ChatPreviewInfoAdapter(this);
        binding.chatsRv.setAdapter(chatPreviewInfoAdapter);
        binding.chatsRv.setLayoutManager(llm);

        // set behaviour
        binding.chatsAddContact.setOnClickListener(view -> {
            androidx.appcompat.app.AlertDialog.Builder dialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);

            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.alert_layout, null);

            dialogBuilder.setView(dialogView);
            Button ok = (Button) dialogView.findViewById(R.id.alert_okay_btn);
            Button cancel = (Button) dialogView.findViewById(R.id.alert_cancel_btn);

            EditText input = (EditText) dialogView.findViewById(R.id.alert_input);
            TextView inputLabel = (TextView) dialogView.findViewById(R.id.alert_input_label);
            inputLabel.setText("Enter contact name");

            EditText input2 = (EditText) dialogView.findViewById(R.id.alert_input2);
            TextView inputLabel2 = (TextView) dialogView.findViewById(R.id.alert_input_label2);
            inputLabel2.setText("Enter contact server");

            TextView title = (TextView) dialogView.findViewById(R.id.alert_title);
            title.setText("Add contact");

            ok.setOnClickListener(view1 -> {
                String name = input.getText().toString(), server = input2.getText().toString();
                dataManager.addContact(new Chat(name, server, "dummyImg"));
                chatPreviewInfoList.clear();
                chatPreviewInfoList.addAll(dataManager.getContacts(MyApplication.token));
                chatPreviewInfoAdapter.notifyDataSetChanged();
                alertDialog.dismiss();
            });
            cancel.setOnClickListener(view1 -> {

                alertDialog.dismiss();
            });

            alertDialog = dialogBuilder.create();

            alertDialog.show();
        });

        binding.chatsLogoutBtn.setOnClickListener(view -> {
            finish();
        });
        MyApplication.chatsDisplay = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.context = this;
        refresh();
    }

    public void refresh() {
        chatPreviewInfoList.clear();
        chatPreviewInfoList.addAll(dataManager.getContacts(MyApplication.token));
        chatPreviewInfoAdapter.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void public_notify(Message newMessage) {
        super.public_notify(newMessage);
        //refresh();
        System.out.println("Chats notified");
    }

    public ActivityChatsBinding getBinding() {
        return binding;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public List<ChatPreviewInfo> getChatPreviewInfoList() {
        return chatPreviewInfoList;
    }

    public List<String> getChatTitles() {
        return chatTitles;
    }

}