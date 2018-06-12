package com.example.obito.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.obito.R;
import com.example.obito.model.News;

import java.net.URL;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private List<News> mNewsList;

    public NewsAdapter(List<News> NewsList) {
        mNewsList = NewsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_right_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News news=mNewsList.get(position);
        holder.newsImageView.setImageURI(Uri.parse(news.getImage().getUrl()));
        holder.newsTitleTextView.setText(news.getTitile());
        holder.newsTimeTextView.setText(news.getDateTme().toString());
        holder.newsSourceTextView.setText(news.getSource());
        holder.newsCommentTextView.setText(news.getComments().get(0).getCount());
        holder.newsGoodTextView.setText(news.getComments().get(0).getThumbUp());
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView newsTitleTextView;
        TextView newsTimeTextView;
        TextView newsSourceTextView;
        TextView newsCommentTextView;
        TextView newsGoodTextView;
        ImageView newsImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            newsTitleTextView=(TextView) itemView.findViewById(R.id.newsTitle_textView);
            newsTimeTextView=(TextView)itemView.findViewById(R.id.newsTime_textView);
            newsSourceTextView=(TextView)itemView.findViewById(R.id.newsSource_textView);
            newsCommentTextView=(TextView)itemView.findViewById(R.id.newsComment_textView);
            newsGoodTextView=(TextView)itemView.findViewById(R.id.newsGood_textView);
            newsImageView=(ImageView)itemView.findViewById(R.id.news_imageView);
        }

    }
}
