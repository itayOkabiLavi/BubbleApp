package com.example.bubbleapp.chatsactivitypack;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bubbleapp.MyApplication;
import com.example.bubbleapp.R;
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

            //String[] dateTime = messageData.parseCreationTime();
            MessageBinding.msgTime.setText(messageData.parseCreationTime());

            setMessageDesign(MessageBinding, messageData);
        }

        @SuppressLint("ResourceAsColor")
        private void setMessageDesign(MessageItemBinding messageItemBinding, Message message) {
            int bg, txt, side;
            boolean darkMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES;
            if (message.fromId.equals(MyApplication.user.name) || message.fromId.equals(MyApplication.user.name + "*" + MyApplication.user.server)) {
                if (darkMode) {
                    bg = 0x004E4E;
                    txt = Color.WHITE;
                } else {
                    bg = 0x70DADA;
                    txt = Color.BLACK;
                }
                side = View.LAYOUT_DIRECTION_LTR;
            } else {
                if (darkMode) {
                    bg = R.color.dark_msg2;
                    txt = Color.WHITE;
                } else {
                    bg = R.color.light_msg2;
                    txt = Color.BLACK;
                }
                side = View.LAYOUT_DIRECTION_RTL;
            }
            MessageBinding.msgContainer.setLayoutDirection(side);
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

