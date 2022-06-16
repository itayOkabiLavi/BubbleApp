package com.example.bubbleapp.chatsactivitypack;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bubbleapp.databinding.MessageItemBinding;
import com.example.bubbleapp.models.Message;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    //private DateTimeFormatter dateTimeFormat;
    private final List<Message> MessageList;
    private DateTimeFormatter messageTimeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

    class MessageViewHolder extends RecyclerView.ViewHolder {
        private MessageItemBinding MessageBinding;
        MessageViewHolder(MessageItemBinding chatListItemBinding) {
            super(chatListItemBinding.getRoot());
            this.MessageBinding = chatListItemBinding;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void setMessageData(Message messageData) {
            MessageBinding.msgText.setText(messageData.getContent());
            MessageBinding.msgTime.setText(messageData.getCreationTime().format(messageTimeFormat));
            MessageBinding.msgChatID.setText(Integer.toString(messageData.getChatId()));
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

