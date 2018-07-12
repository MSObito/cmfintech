package com.example.obito.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import android.widget.ToggleButton;

import com.example.obito.R;
import com.example.obito.adapter.NewsAdapter;
import com.example.obito.fragment.NewsFragment;
import com.example.obito.model.News;
import com.example.obito.model.NewsBean;
import com.example.obito.utils.JsonUtil;
import com.example.obito.utils.SdCardManager;
import com.example.obito.utils.Share;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
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
    private ToggleButton subscribeToggleButton;
    private Toolbar toolbar;

    String fileName;
    private News news;
    boolean isTop;
    private NewsBean newsBean;
    private List<Bitmap> imageViewList=new ArrayList<>();
    private ArrayList<String> imageUriList=new ArrayList<>();

    public static final String IMAGES_LIST="image_list";
    public static final String POSITION="image_position";
    public static final String NEWSBEAN="newsbean";
    public static final String ISTOP="istop";

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
        subscribeToggleButton=(ToggleButton)findViewById(R.id.subscribeToggleButton);
        subscribeToggleButton.setBackgroundColor(getResources().getColor(R.color.colorDarkGray));
        commentFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        commentsFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),CommentActivity.class);
                intent.putExtra(NEWSBEAN,newsBean);
                intent.putExtra(ISTOP,isTop);
                startActivity(intent);
            }
        });
        collectImageView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Drawable.ConstantState collect=  collectImageView.getDrawable().getCurrent().getConstantState();
                Drawable.ConstantState collect1=getDrawable(R.mipmap.icon_collect).getConstantState();
                if(collect.equals(collect1))
                {
                    collectImageView.setImageResource(R.mipmap.icon_collect2);
                    SdCardManager.saveFile(view.getContext(),"collect",news.getId(),news);
                }
                else{
                    collectImageView.setImageResource(R.mipmap.icon_collect);
                    SdCardManager.deleteFile(view.getContext(),"collect",news.getId());
                }
            }
        });
        shareImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Share share=new Share();
                share.shareText(view.getContext(),newsBean.getTitile());
            }
        });
        subscribeToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //subscribeToggleButton.setChecked(!subscribeToggleButton.isChecked());
                if(subscribeToggleButton.isChecked()){
                    subscribeToggleButton.setBackgroundColor(getResources().getColor(R.color.colorBlue));
                }else{
                    subscribeToggleButton.setBackgroundColor(getResources().getColor(R.color.colorDarkGray));
                }
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
        news=(News)intent.getSerializableExtra(NewsAdapter.NEWS_ENTITY);
        isTop=intent.getBooleanExtra(NewsAdapter.NEWS_TOP,false);
        if(isTop){
            topTextView.setVisibility(View.VISIBLE);
        }
        String jsonData=JsonUtil.getJson(fileName,this);
        newsBean=JsonUtil.parseJSON(jsonData, NewsBean.class);
        if(newsBean.getTitile()==null){
            Toast.makeText(this,"无内容",Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        titleTextView.setText(newsBean.getTitile());
        fromTextView.setText(newsBean.getSource());
        dateTextView.setText(newsBean.getDateTme());
        if(newsBean.getLike()){
            subscribeToggleButton.setChecked(true);
            subscribeToggleButton.setBackgroundColor(Color.BLUE);
        }
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
        textView.setTextSize(17);
        textView.setTextColor(getResources().getColor(R.color.colorHeader));
        TextPaint textPaint=textView.getPaint();
        textPaint.setFakeBoldText(true);
        textView.setLineSpacing(0,1.3f);
        return textView;
    }

    private View addPTextView(String text){
        TextView textView=new TextView(this);
        textView.setText(text);
        textView.setTextSize(17);
        textView.setTextColor(getResources().getColor(R.color.colorHeader));
        textView.setLineSpacing(0,1.3f);
        return textView;
    }

    private View addImageView(final String source){
        final ImageView imageView=new ImageView(this);
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(layoutParams);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setPadding(8,20,8,20);
        Target target=new Target() {

            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                imageViewList.add(bitmap);
                imageView.setImageBitmap(bitmap);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//              Toast.makeText(view.getContext(),source,Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(view.getContext(),ImagesActivity.class);
//                        intent.putExtra(IMAGES_LIST,(Serializable)imageViewList);
                        intent.putStringArrayListExtra(IMAGES_LIST,imageUriList);
                        int position=imageViewList.indexOf(bitmap);
                        intent.putExtra(POSITION,position);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        Picasso.get()
                .load(source)
                .placeholder(R.mipmap.img_load_fail)
                .error(R.mipmap.img_load_fail2)
                .into(target);
        imageUriList.add(source);
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
