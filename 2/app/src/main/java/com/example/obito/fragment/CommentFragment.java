package com.example.obito.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.obito.R;
import com.example.obito.activity.NewsActivity;
import com.example.obito.adapter.CommentAdapter;
import com.example.obito.model.NewsBean;
import com.example.obito.utils.SdCardManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommentFragment extends Fragment {

    private RecyclerView commentRecyclerView;
    public static CommentAdapter commentAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_comment,container,false);
        NewsBean newsBean=(NewsBean)getArguments().get(NewsActivity.NEWSBEAN);
        commentRecyclerView=(RecyclerView)view.findViewById(R.id.commentRecyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        commentRecyclerView.setLayoutManager(layoutManager);
        commentAdapter=new CommentAdapter(newsBean.getComments(),getActivity());
        commentRecyclerView.setAdapter(commentAdapter);
        return view;
    }

    /**
     * 这个方法2022年就失效了。。。
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(commentAdapter.getChangedCommentsList()==null){
            return;
        }
        List<NewsBean.Comments> commentsList=commentAdapter.getChangedCommentsList();
        if(commentsList.size()!=0){
            String date=new SimpleDateFormat("yyMdHms").format(new Date());
            int time=Integer.parseInt(date);
            for (NewsBean.Comments comments:commentsList) {
                try{
                    time++;
                    SdCardManager.saveObject(getContext(),Float.toString(time),comments);
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("CommentActivity","sdcaed存储点赞的comment失败！");
                }
            }
        }
    }
}
