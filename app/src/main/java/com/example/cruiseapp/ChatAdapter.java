package com.example.cruiseapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatAdapter  extends RecyclerView.Adapter<ChatAdapter.myCustomChatAdapterHolder> {
    ArrayList<Message> messages=new ArrayList<>();
    public ChatAdapter(ArrayList<Message> messages){
        this.messages=messages;
    }
    @NonNull
    @Override
    public ChatAdapter.myCustomChatAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.layout_chat_list,viewGroup,false);
        ChatAdapter.myCustomChatAdapterHolder chatAdapterHolder=new ChatAdapter.myCustomChatAdapterHolder(view);
        return chatAdapterHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.myCustomChatAdapterHolder myCustomChatAdapterHolder, int i) {
    Message message=messages.get(i);
        Log.d(">>>>>>>>>>---", "onBindViewHolder: "+message);
    myCustomChatAdapterHolder.txt_uname.setText(message.getUserName());
    myCustomChatAdapterHolder.txt_msg.setText(message.getMessage());
    }

    @Override
    public int getItemCount() {
        return messages.size();

    }

    public class myCustomChatAdapterHolder extends RecyclerView.ViewHolder {
        TextView txt_uname,txt_msg;
        public myCustomChatAdapterHolder(@NonNull View itemView) {
            super(itemView);
            txt_uname=itemView.findViewById(R.id.txt_uName);
            txt_msg=itemView.findViewById(R.id.txt_message);
        }
    }
}


