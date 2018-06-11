package com.example.obito.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.obito.utils.StatusBarUtil;

import static android.view.View.GONE;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.transparencyBar(this);
        StatusBarUtil.StatusBarLightMode(this);

    }

    /**
     * 将导航栏隐藏
     * @param activity
     * @param visible
     */
    public static void setNavigationBar(Activity activity, int visible){
        View decorView = activity.getWindow().getDecorView();
        //显示NavigationBar
        if (View.GONE == visible){
            int option = SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            decorView.setSystemUiVisibility(option);
        }
    }
}
