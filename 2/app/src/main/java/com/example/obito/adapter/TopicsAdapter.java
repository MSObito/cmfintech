package com.example.obito.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.obito.R;

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
            ViewHolder holder=new ViewHolder(view);
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
