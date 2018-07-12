package com.example.obito.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.obito.R;
import com.example.obito.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<Message> messageList;

    public MessageAdapter(List<Message> messageList) {
        super();
        this.messageList=messageList;
    }

    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_item, parent, false);
        MessageAdapter.ViewHolder holder = new MessageAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message=messageList.get(position);
        holder.nameTitleTextView.setText(message.getName());
        holder.dateTextView.setText(message.getDate());
        holder.messageTextView.setText(message.getMessage());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameTitleTextView;
        TextView dateTextView;
        TextView messageTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            nameTitleTextView=(TextView) itemView.findViewById(R.id.nameTextView);
            dateTextView=(TextView)itemView.findViewById(R.id.dateTextView);
            messageTextView=(TextView)itemView.findViewById(R.id.messageTextView);
        }
    }
}
