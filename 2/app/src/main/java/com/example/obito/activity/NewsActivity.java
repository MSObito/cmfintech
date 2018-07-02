package com.example.obito.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.obito.R;
import com.example.obito.adapter.NewsAdapter;
import com.example.obito.model.NewsBean;
import com.example.obito.utils.JsonUtil;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Method;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends BaseActivity {

    private TextView titleTextView;
    private TextView fromTextView;
    private TextView dateTextView;
    private ImageView fromImageView;
    private TextView topTextView;
    private TextView commentCountTextView;
    private FrameLayout commentFrameLayout;
    private FrameLayout commentsFrameLayout;
    private ImageView collectImageView;
    private ImageView shareImageView;
    private LinearLayout contentLayout;

    private Toolbar toolbar;

    String fileName;
    boolean isTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        toolbar=(Toolbar)findViewById(R.id.backToolbar);
        titleTextView=(TextView)findViewById(R.id.titleTextView);
        fromTextView=(TextView)findViewById(R.id.fromTextView);
        dateTextView=(TextView)findViewById(R.id.timeTextView);
        fromImageView=(ImageView)findViewById(R.id.fromImageView);
        topTextView=(TextView)findViewById(R.id.topTextView);
        commentCountTextView=(TextView)findViewById(R.id.commentCountTextView);
        commentFrameLayout=(FrameLayout)findViewById(R.id.commentLayout);
        commentsFrameLayout=(FrameLayout)findViewById(R.id.commentsFrameLayout);
        collectImageView=(ImageView)findViewById(R.id.collectImageView);
        shareImageView=(ImageView)findViewById(R.id.shareImageView);
        contentLayout=(LinearLayout)findViewById(R.id.contentLayout);
        commentFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        commentsFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        collectImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        shareImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        setSupportActionBar(toolbar);
        ActionBar actionBar =  getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        Intent intent=getIntent();
        fileName=intent.getStringExtra(NewsAdapter.NEWS_ID)+".json";
        isTop=intent.getBooleanExtra(NewsAdapter.NEWS_TOP,false);
        if(isTop){
            topTextView.setVisibility(View.VISIBLE);
        }
        String jsonData=JsonUtil.getJson(fileName,this);
        NewsBean newsBean=JsonUtil.parseJSON(jsonData, NewsBean.class);
        if(newsBean==null){
            Toast.makeText(this,"无内容",Toast.LENGTH_SHORT).show();
            return;
        }
        titleTextView.setText(newsBean.getTitile());
        fromTextView.setText(newsBean.getSource());
        dateTextView.setText(newsBean.getDateTme());
        commentCountTextView.setText(String.valueOf(newsBean.getComments().size()));
        List<NewsBean.Content> contents=newsBean.getContent();
        for (NewsBean.Content content: contents) {
            if(content.getType().equals("h1")){
                contentLayout.addView(addHTextView(content.getText()));
            }else if(content.getType().equals("p")){
                contentLayout.addView(addPTextView(content.getText()));
            }else if(content.getType().equals("image")){
                contentLayout.addView(addImageView(content.getText()));
            }
        }
    }

    private View addHTextView(String text){
        TextView textView=new TextView(this);
        textView.setText(text);
        textView.setTextSize(15);
        textView.setTextColor(Color.BLACK);
        TextPaint textPaint=textView.getPaint();
        textPaint.setFakeBoldText(true);
        textView.setLineSpacing(0,1.2f);
        return textView;
    }

    private View addPTextView(String text){
        TextView textView=new TextView(this);
        textView.setText(text);
        textView.setTextSize(15);
        textView.setTextColor(Color.BLACK);
        textView.setLineSpacing(0,1.2f);
        return textView;
    }

    private View addImageView(final String source){
        ImageView imageView=new ImageView(this);
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(layoutParams);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setPadding(8,20,8,20);
        Picasso.get()
                .load(source)
                .placeholder(R.mipmap.img_load_fail)
                .error(R.mipmap.img_load_fail2)
                .into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),source,Toast.LENGTH_SHORT).show();
            }
        });
        return imageView;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.removeActivity(this);
    }
}
