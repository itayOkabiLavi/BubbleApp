package com.example.bubbleapp.chatsactivitypack;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bubbleapp.databinding.ChatMessageItemBinding;

import java.util.List;

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.MessageViewHolder> {
    //private DateTimeFormatter dateTimeFormat;
    private final List<ChatMessage> chatMessageList;

    class MessageViewHolder extends RecyclerView.ViewHolder {
        private ChatMessageItemBinding chatMessageBinding;
        MessageViewHolder(ChatMessageItemBinding chatListItemBinding) {
            super(chatListItemBinding.getRoot());
            this.chatMessageBinding = chatListItemBinding;
        }

        public void setMessageData(ChatMessage messageData) {
            chatMessageBinding.msgText.setText(messageData.getText());
            chatMessageBinding.msgTime.setText(messageData.getTime().toString());
            /*
            chatMessageBinding.userCard.setOnClickListener(x -> {
                Intent intent = new Intent(context, ChatDisplayActivity.class);
                intent.putExtra("chatinfo", userData);
                intent.putExtra("token", container.getToken());
                context.startActivity(intent);
            });
            chatListItemBinding.userCard.setOnLongClickListener(x -> {
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
            chatListItemBinding.lastmessageTime.setText(userData.getLastMessageDate().toString());
            //IMAGE

             */
        }
    }

    public ChatMessageAdapter(List<ChatMessage> messageList) {
        this.chatMessageList = messageList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChatMessageItemBinding chatMessageItemBinding = ChatMessageItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new MessageViewHolder(chatMessageItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        holder.setMessageData(chatMessageList.get(position));
    }

    @Override
    public int getItemCount() {
        return chatMessageList.size();
    }

}

