package com.example.obito.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.obito.R;
import com.example.obito.fragment.MeFragment;
import com.example.obito.fragment.NewsFragment;
import com.example.obito.fragment.OrderFragment;
import com.example.obito.utils.NetworkManager;
import com.example.obito.views.BottomBar;

import java.lang.reflect.Method;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomBar bottomBar = findViewById(R.id.bottom_bar);
        bottomBar.setContainer(R.id.fl_container)
                .setTitleBeforeAndAfterColor("#999999", "#000000")
                .addItem(NewsFragment.class,
                        "新闻",
                        R.mipmap.news_grey,
                        R.mipmap.news_blue)
                .addItem(OrderFragment.class,
                        "订阅",
                        R.mipmap.order_grey,
                        R.mipmap.order_blue)
                .addItem(MeFragment.class,
                        "我",
                        R.mipmap.me_grey,
                        R.mipmap.me_blue)
                .build();
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if(!NetworkManager.ping()){
            Intent intent=new Intent(MainActivity.this,ErrorActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {

    }
}
