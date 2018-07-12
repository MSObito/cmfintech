package com.example.obito.adapter;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.obito.R;
import com.example.obito.activity.CommentActivity;
import com.example.obito.model.NewsBean;
import com.example.obito.utils.Base64Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private List<NewsBean.Comments> commentsList;
    private Activity activity;
    private static List<NewsBean.Comments> changedCommentsList = new ArrayList<>();

    public CommentAdapter(List<NewsBean.Comments> comments) {
        super();
        this.commentsList = comments;
    }

    public CommentAdapter(List<NewsBean.Comments> commentsList, Activity activity) {
        super();
        this.commentsList = commentsList;
        this.activity = activity;
    }

    public List<NewsBean.Comments> getCommentsList() {
        return commentsList;
    }

    public List<NewsBean.Comments> getChangedCommentsList() {
        return changedCommentsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);
        final CommentAdapter.ViewHolder holder = new CommentAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(activity.getClass()==CommentActivity.class){
            boolean liked = false;
            final NewsBean.Comments comments = commentsList.get(position);
            holder.commentTextView.setText(comments.getText());
            holder.dateTextView.setText(comments.getDateTime());
            holder.thumbTextView.setText(Integer.toString(comments.getThumbUp()));
            holder.usrTextView.setText(comments.getName());
            String[] icon = comments.getIcon().split("data:image/png;base64,");
            holder.usrImageView.setImageBitmap(Base64Util.stringtoBitmap(icon[1]));
            holder.thumbImageView.setImageResource(R.mipmap.icon_good);
            holder.thumbImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.thumbImageView.getTag().equals("unSelect")) {
                        holder.thumbImageView.setTag("Select");
                        holder.thumbImageView.setImageResource(R.mipmap.icon_good2);
                        comments.setThumbUp(comments.getThumbUp() + 1);
                        holder.thumbTextView.setText(Integer.toString(comments.getThumbUp()));
                        changedCommentsList.add(comments);
                    } else {
                        holder.thumbImageView.setTag("unSelect");
                        holder.thumbImageView.setImageResource(R.mipmap.icon_good);
                        comments.setThumbUp(comments.getThumbUp() - 1);
                        holder.thumbTextView.setText(Integer.toString(comments.getThumbUp()));
                        if (changedCommentsList.contains(comments))
                            changedCommentsList.remove(comments);
                    }
                }
            });
        }else{
            final NewsBean.Comments comments=commentsList.get(position);
            holder.commentTextView.setText(comments.getText());
            holder.dateTextView.setVisibility(View.GONE);
            holder.thumbTextView.setVisibility(View.GONE);
            holder.usrTextView.setText(comments.getName());
            String[] icon = comments.getIcon().split("data:image/png;base64,");
            holder.usrImageView.setImageBitmap(Base64Util.stringtoBitmap(icon[1]));
            holder.thumbImageView.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    public void addData(int position, NewsBean.Comments comments) {
//         在list中添加数据，并通知条目加入一条
        commentsList.add(position, comments);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        commentsList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView usrTextView;
        TextView thumbTextView;
        TextView commentTextView;
        TextView dateTextView;
        ImageView thumbImageView;
        ImageView usrImageView;


        public ViewHolder(View itemView) {
            super(itemView);
            usrTextView = (TextView) itemView.findViewById(R.id.usrTextView);
            thumbTextView = (TextView) itemView.findViewById(R.id.thumbTextView);
            commentTextView = (TextView) itemView.findViewById(R.id.commentTextView);
            dateTextView = (TextView) itemView.findViewById(R.id.dateTextView);
            thumbImageView = (ImageView) itemView.findViewById(R.id.thumbImageView);
            usrImageView = (ImageView) itemView.findViewById(R.id.usrImageView);
        }
    }
}
