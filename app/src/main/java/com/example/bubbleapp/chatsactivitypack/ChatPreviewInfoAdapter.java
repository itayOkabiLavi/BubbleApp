package com.example.bubbleapp.chatsactivitypack;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bubbleapp.ChatsActivity;
import com.example.bubbleapp.R;
import com.example.bubbleapp.databinding.ChatListItemBinding;

import java.util.List;
import java.util.Objects;

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

            chatListItemBinding.chatsCard.setOnClickListener(x -> {
                Intent intent = new Intent(context, ChatDisplayActivity.class);
                intent.putExtra("server", userData.getChat().getServer());
                intent.putExtra("chatId", userData.getChat().getContactName());
                intent.putExtra("chatAddressee", userData.getChat().getContactName());
                context.startActivity(intent);
            });
            chatListItemBinding.chatsCard.setOnLongClickListener(x -> {
                new AlertDialog.Builder(context)
                        .setTitle("Warning")
                        .setMessage("Delete this chat?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
                return false;
            });
            chatListItemBinding.userName.setText(userData.getContactName());
            chatListItemBinding.lastmessageText.setText(userData.getLastMessage());
            if (userData.getProfilePicture() != null && !Objects.equals(userData.getProfilePicture(), "")) {
                byte[] decodedString = Base64.decode(userData.getProfilePicture(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                chatListItemBinding.userImage.setImageBitmap(decodedByte);
            } else {
                chatListItemBinding.userImage.setImageResource(R.drawable.generic_profile_image);
            }
            if (userData.getLastMessageDate() == null)
                chatListItemBinding.lastmessageTime.setText("");
            else
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
