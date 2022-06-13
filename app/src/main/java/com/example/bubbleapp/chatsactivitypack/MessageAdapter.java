package com.example.bubbleapp.chatsactivitypack;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bubbleapp.databinding.MessageItemBinding;
import com.example.bubbleapp.models.Message;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    //private DateTimeFormatter dateTimeFormat;
    private final List<Message> MessageList;

    class MessageViewHolder extends RecyclerView.ViewHolder {
        private MessageItemBinding MessageBinding;
        MessageViewHolder(MessageItemBinding chatListItemBinding) {
            super(chatListItemBinding.getRoot());
            this.MessageBinding = chatListItemBinding;
        }

        public void setMessageData(Message messageData) {
            MessageBinding.msgText.setText(messageData.getContent());
            MessageBinding.msgTime.setText(messageData.getCreatedAt());
            /*
            MessageBinding.userCard.setOnClickListener(x -> {
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

    public MessageAdapter(List<Message> messageList) {
        this.MessageList = messageList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MessageItemBinding MessageItemBinding =
                com.example.bubbleapp.databinding.MessageItemBinding.inflate(
                    LayoutInflater.from(parent.getContext()),
                    parent,
                    false
                );
        return new MessageViewHolder(MessageItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        holder.setMessageData(MessageList.get(position));
    }

    @Override
    public int getItemCount() {
        return MessageList.size();
    }

}

