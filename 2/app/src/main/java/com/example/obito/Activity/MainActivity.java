package com.example.obito.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.obito.R;
import com.example.obito.fragment.MeFragment;
import com.example.obito.fragment.NewsFragment;
import com.example.obito.fragment.OrderFragment;
import com.example.obito.utils.NetworkManager;
import com.example.obito.views.BottomBar;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomBar bottomBar = findViewById(R.id.bottom_bar);
        bottomBar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        bottomBar.setContainer(R.id.fl_container)
                .setTitleBeforeAndAfterColor(getResources().getColor(R.color.colorNoChoose), getResources().getColor(R.color.colorChoose))
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
        if(!NetworkManager.ping()){
            Intent intent=new Intent(MainActivity.this,ErrorActivity.class);
            startActivity(intent);
        }
    }
}
