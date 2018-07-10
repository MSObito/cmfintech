package com.example.obito.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.obito.R;
import com.example.obito.adapter.CommentAdapter;
import com.example.obito.fragment.CommentFragment;
import com.example.obito.fragment.NoCommentsFragment;
import com.example.obito.model.NewsBean;
import com.example.obito.popup.CommentPopup;
import com.example.obito.utils.SdCardManager;
import com.example.obito.utils.Share;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommentActivity extends BaseActivity {

    private Toolbar toolbar;
    private ImageView shareImageView;
    private LinearLayout backLayout;
    private FrameLayout commentLayout;
    private TextView titleTextView;
    private TextView timeTextView;
    private TextView fromTextView;
    private TextView topTextView;
    private TextView allCommentsTextView;

    private NewsBean newsBean;
    private boolean isTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        toolbar=(Toolbar)findViewById(R.id.backToolbar);
        shareImageView=(ImageView)findViewById(R.id.shareImageView);
        backLayout=(LinearLayout)findViewById(R.id.backNewsLayout);
        commentLayout=(FrameLayout)findViewById(R.id.commentLayout);
        titleTextView=(TextView)findViewById(R.id.titleTextView2);
        timeTextView=(TextView)findViewById(R.id.timeTextView);
        fromTextView=(TextView)findViewById(R.id.fromTextView);
        topTextView=(TextView)findViewById(R.id.topTextView);
        allCommentsTextView=(TextView)findViewById(R.id.allCommentsTextView);
        setSupportActionBar(toolbar);
        ActionBar actionBar =  getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        newsBean=(NewsBean)getIntent().getSerializableExtra(NewsActivity.NEWSBEAN);
        isTop=getIntent().getBooleanExtra(NewsActivity.ISTOP,false);
        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        commentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommentPopup commentPopup=new CommentPopup(view.getContext());
                commentPopup.showPopupWindow();
            }
        });
        shareImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Share share=new Share();
                share.shareText(view.getContext(),newsBean.getTitile());
            }
        });
        titleTextView.setText(newsBean.getTitile());
        timeTextView.setText(newsBean.getDateTme());
        fromTextView.setText(newsBean.getSource());
        if(isTop){
            topTextView.setVisibility(View.VISIBLE);
        }
        allCommentsTextView.setText("全部评论（"+newsBean.getComments().size()+"）");
        if(newsBean.getComments().size()==0){
            NoCommentsFragment noCommentsFragment=new NoCommentsFragment();
            replaceFragment(noCommentsFragment);
        }else{
            Bundle bundle=new Bundle();
            bundle.putSerializable(NewsActivity.NEWSBEAN,newsBean);
            CommentFragment commentFragment=new CommentFragment();
            replaceFragment(commentFragment,bundle);
        }
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

    private void replaceFragment(Fragment fragment,Bundle bundle){
        fragment.setArguments(bundle);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.replaceFrameLayout,fragment);
        transaction.commit();
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.replaceFrameLayout,fragment);
        transaction.commit();
    }
}
