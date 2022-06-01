package com.example.bubbleapp.chatsactivitypack;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bubbleapp.databinding.ChatListItemBinding;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ContactViewHolder> {
    //private DateTimeFormatter dateTimeFormat;
    private final List<ChatPreviewInfo> chatPreviewInfoList;

    public ChatAdapter(List<ChatPreviewInfo> chatPreviewInfoList) {
        this.chatPreviewInfoList = chatPreviewInfoList;
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        ChatListItemBinding chatListItemBinding;
        private Context context;
        ContactViewHolder(ChatListItemBinding chatListItemBinding) {
            super(chatListItemBinding.getRoot());
            this.chatListItemBinding = chatListItemBinding;
            context = chatListItemBinding.getRoot().getContext();
        }

        public void setUserData(ChatPreviewInfo userData) {
            chatListItemBinding.userCard.setOnClickListener(x -> {
                Intent intent = new Intent(context, ChatDisplayActivity.class);
                intent.putExtra("chatinfo", userData);
                context.startActivity(intent);
            });
            chatListItemBinding.userName.setText(userData.getContactName());
            chatListItemBinding.lastmessageText.setText(userData.getLastMessage());
            chatListItemBinding.lastmessageTime.setText(userData.getLastMessageDate().toString());
            //IMAGE
        }
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChatListItemBinding chatListItemBinding = ChatListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ContactViewHolder(chatListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.setUserData(chatPreviewInfoList.get(position));
    }

    @Override
    public int getItemCount() {
        return chatPreviewInfoList.size();
    }

    /*

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ChatAdapter(@NonNull Context context, List<ChatInfo> resource) {
        super(context, 0, resource);
        dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }
*/

//    private Bitmap getUserImage(String encodedImage) {}

/*
    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ChatInfo chat = (ChatInfo) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_chat_item, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.chat_name);
        TextView tvLMtext = (TextView) convertView.findViewById(R.id.lastmessage_text);
        TextView tvLMtime = (TextView) convertView.findViewById(R.id.lastmessage_time);
        tvName.setText(chat.getContactName());
        tvLMtext.setText(chat.getLastMessage());
        tvLMtime.setText(chat.getLastMessageDate().format(this.dateTimeFormat.toString()));
        return convertView;
    }
    */
}
