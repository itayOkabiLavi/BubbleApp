package com.example.bubbleapp.chatsactivitypack;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bubbleapp.MyApplication;
import com.example.bubbleapp.databinding.MessageItemBinding;
import com.example.bubbleapp.models.Message;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
            /*
            String[] date = messageData.created.split("T");
            String time = date[1].split("\\.")[0].substring(0, 5);

             */
            String dateTime = DateFormat.getDateTimeInstance().format(
                    new Date(messageData.creationTime)
            );
            MessageBinding.msgTime.setText(dateTime);

            setMessageDesign(MessageBinding, messageData);
        }
        private void setMessageDesign(MessageItemBinding messageItemBinding, Message message) {
            int bg, txt;
            boolean darkMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES;
            if (message.fromId.equals(MyApplication.user.name)) {
                if (darkMode) {
                    bg = Color.GREEN;
                    txt = Color.WHITE;
                } else {
                    bg = Color.GREEN;
                    txt = Color.BLACK;
                }
                // TODO: align to left
            }

            else {
                if (darkMode) {
                    bg = Color.DKGRAY;
                    txt = Color.WHITE;
                } else {
                    bg = Color.LTGRAY;
                    txt = Color.BLACK;
                }
            }
            MessageBinding.msgCard.setCardBackgroundColor(bg);
            MessageBinding.msgText.setTextColor(txt);

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

