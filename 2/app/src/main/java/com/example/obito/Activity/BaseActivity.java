package com.example.obito.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.obito.R;
import com.example.obito.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

public class BaseActivity extends AppCompatActivity {

    private static boolean enableNightMode = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sf=getSharedPreferences("data", MODE_PRIVATE);//判断是否是第一次进入
        enableNightMode=sf.getBoolean("nightMode", false);
        if(!enableNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            StatusBarUtil.transparencyBar(this);
            StatusBarUtil.StatusBarLightMode(this);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            StatusBarUtil.transparencyBar(this);
            StatusBarUtil.setStatusBarColor(this,R.color.colorPrimary);
        }
        ActivityManager.addActivity(this);
    }

    /**
     *If enabled night mode
     * @return  true or false
     */
    public boolean isEnableNightMode() {
        return enableNightMode;
    }

    /**
     * enable night mode or not
     * @param enableNightMode   true or false
     */
    public void setEnableNightMode(boolean enableNightMode) {
        this.enableNightMode = enableNightMode;
        if(enableNightMode) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.removeActivity(this);
    }
}
