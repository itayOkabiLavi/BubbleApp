package com.example.bubbleapp.chatsactivitypack;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bubbleapp.ChatsActivity;
import com.example.bubbleapp.databinding.ChatListItemBinding;

import java.util.List;

public class ChatPreviewInfoAdapter extends RecyclerView.Adapter<ChatPreviewInfoAdapter.ContactViewHolder> {
    //private DateTimeFormatter dateTimeFormat;
    private final List<ChatPreviewInfo> chatPreviewInfoList;
    private final ChatsActivity container;

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private ChatListItemBinding chatListItemBinding;
        private ChatsActivity container;

        private Context context;
        ContactViewHolder(ChatListItemBinding chatListItemBinding,
                          ChatsActivity chatsActivity) {
            super(chatListItemBinding.getRoot());
            this.chatListItemBinding = chatListItemBinding;
            context = chatListItemBinding.getRoot().getContext();
            this.container = chatsActivity;
        }

            public void setUserData(ChatPreviewInfo userData) {
                chatListItemBinding.userCard.setOnClickListener(x -> {
                    Intent intent = new Intent(context, ChatDisplayActivity.class);

                    intent.putExtra("chatinfo", userData);
                    context.startActivity(intent);
                });
                chatListItemBinding.userCard.setOnLongClickListener(x -> {
                    new AlertDialog.Builder(context)
                            .setTitle("Warning")
                            .setMessage("Delete this message?")
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    chatPreviewInfoList.remove(userData);
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                    return false;
                });
                chatListItemBinding.userName.setText(userData.getContactName());
                chatListItemBinding.lastmessageText.setText(userData.getLastMessage());
                chatListItemBinding.lastmessageTime.setText(userData.getLastMessageDate().toString());
                //IMAGE
            }
        }

    public ChatPreviewInfoAdapter(ChatsActivity chatsActivity) {
        this.container = chatsActivity;
        this.chatPreviewInfoList = chatsActivity.getChatPreviewInfoList();
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChatListItemBinding chatListItemBinding = ChatListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ContactViewHolder(chatListItemBinding, this.container);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.setUserData(chatPreviewInfoList.get(position));
    }

    @Override
    public int getItemCount() {
        return chatPreviewInfoList.size();
    }

}
