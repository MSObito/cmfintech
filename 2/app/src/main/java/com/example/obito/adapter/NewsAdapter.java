package com.example.obito.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.obito.R;
import com.example.obito.model.News;
import com.example.obito.presenter.NewsInterface;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private List<News> mNewsList;
    private Context mContext;
    private static final int TYPE_ITEM_RIGHT=0;
    private static final int TYPE_ITEM_BOTTOM=1;

    public NewsAdapter(List<News> NewsList,Context context) {
        mNewsList = NewsList;
        this.mContext=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder=null;
        if(viewType==TYPE_ITEM_RIGHT){
            View view= LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.news_right_item,parent,false);
            holder=new ViewHolder(view);
        }else if(viewType==TYPE_ITEM_BOTTOM){
            View view=LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.news_bottom_item,parent,false);
            holder=new ViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News news=mNewsList.get(position);
        Picasso.get()
                .load(news.getImage().getUrl())
                .placeholder(R.mipmap.img_load_fail)
                .error(R.mipmap.img_load_fail2)
                .into(holder.newsImageView);
        //holder.newsImageView.setImageURI(Uri.parse(news.getImage().getUrl()));
        holder.newsTitleTextView.setText(news.getTitile());
        holder.newsTimeTextView.setText(news.getDateTme().split(" ")[0]);
        holder.newsSourceTextView.setText(news.getSource());
        holder.newsCommentTextView.setText(Integer.toString(news.getComments().get(0).getCount()));
        holder.newsGoodTextView.setText(Integer.toString(news.getComments().get(0).getThumbUp()));
    }

    @Override
    public int getItemViewType(int position) {
        if(mNewsList.get(position).getImage().getPosition().equals("right")){
            return TYPE_ITEM_RIGHT;
        }else if(mNewsList.get(position).getImage().getPosition().equals("block")){
            return TYPE_ITEM_BOTTOM;
        }else{
            return TYPE_ITEM_RIGHT;
        }
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
