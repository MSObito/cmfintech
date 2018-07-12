package com.example.obito.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.obito.R;
import com.example.obito.adapter.CommentAdapter;
import com.example.obito.adapter.MessageAdapter;
import com.example.obito.model.Comments;
import com.example.obito.model.NewsBean;
import com.example.obito.utils.SdCardManager;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

public class LikedActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView likedRecyclerView;

    private CommentAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked);
        toolbar=(Toolbar)findViewById(R.id.backToolbar);
        likedRecyclerView=(RecyclerView)findViewById(R.id.likedRecyclerView);
        setSupportActionBar(toolbar);
        ActionBar actionBar =  getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        likedRecyclerView.setLayoutManager(layoutManager);
        commentAdapter=new CommentAdapter(initData(),this);
        likedRecyclerView.setAdapter(commentAdapter);
    }

    private List<NewsBean.Comments> initData(){
        List<NewsBean.Comments> commentList=new ArrayList<>();
        List<Object> objectList=SdCardManager.getAll(this);
        for (Object o:objectList) {
            commentList.add((NewsBean.Comments)o);
        }
        return commentList;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
