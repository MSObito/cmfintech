package com.example.obito.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Bundle;

import com.example.obito.R;

public class SplashActivity extends BaseActivity {

    private static final int TIME=750;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sf=getSharedPreferences("data", MODE_PRIVATE);//判断是否是第一次进入
        boolean isFirstIn=sf.getBoolean("isFirstIn", true);
        SharedPreferences.Editor editor=sf.edit();
        if(isFirstIn) {     //若为true，则是第一次进入
            editor.putBoolean("isFirstIn", false);
            Intent intent = new Intent(this, GuideActivity.class);
            editor.commit();
            startActivity(intent);
            finish();
            return;
        }else{
//            editor.putBoolean("isFirstIn", true);
//            editor.commit();
            setContentView(R.layout.activity_splash);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    enterHomeActivity();
                }
            }, TIME);
        }
    }

    private void enterHomeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
