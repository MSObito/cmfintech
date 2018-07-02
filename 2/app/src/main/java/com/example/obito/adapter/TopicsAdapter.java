package com.example.obito.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.obito.R;
import com.example.obito.activity.SearchActivity;

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.ViewHolder> {

    private String[] topics;

    public TopicsAdapter(String[] topics) {
        super();
        this.topics=topics;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.topic_item,parent,false);
            final ViewHolder holder=new ViewHolder(view);
            holder.topicButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText editText=((Activity)view.getContext()).findViewById(R.id.searchView);
                    int position=holder.getAdapterPosition();
                    String topic=topics[position];
                    editText.setText(topic);
                    editText.setSelection(topic.length());
                }
            });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String topic=topics[position];
        holder.topicButton.setText(topic);
    }

    @Override
    public int getItemCount() {
        return topics.length;
    }


    static class ViewHolder extends RecyclerView.ViewHolder{

        Button topicButton;

        public ViewHolder(View itemView) {
            super(itemView);
            topicButton=(Button)itemView.findViewById(R.id.topicButton);
        }
    }
}
