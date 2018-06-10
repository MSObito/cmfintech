package com.example.obito.Activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.obito.R;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity implements View.OnClickListener{

    private ViewPager guideViewPager;
    private Button startButton;
    private List<View> views;
    private PagerAdapter pagerAdapter;

    // 引导页图片资源
    private static final int[] pics = { R.layout.guide_view1,
            R.layout.guide_view2, R.layout.guide_view3 };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_guide);
        views=new ArrayList<View>();
        // 初始化引导页视图列表
        for (int i = 0; i < pics.length; i++) {
            View view = LayoutInflater.from(this).inflate(pics[i], null);

            if (i == pics.length - 1) {
                startButton = (Button) view.findViewById(R.id.btn_login);
                startButton.setTag("enter");
                startButton.setOnClickListener(this);
            }

            views.add(view);

        }
        pagerAdapter=new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view=views.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                View view=views.get(position);
                container.removeView(view);
            }

            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
        };
        guideViewPager=(ViewPager)findViewById(R.id.guideviewpager);
        guideViewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btn_login) {
            Intent intent=new Intent(GuideActivity.this,SplashActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
