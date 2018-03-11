package com.example.obito.webviewtest.Activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.obito.webviewtest.R;

import java.util.Timer;
import java.util.TimerTask;

public class HandlerActivity extends AppCompatActivity {

    //定义周期性显示的图片ID
    int[] imageIds=new int[]{
            R.drawable.castle,
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.love,
            R.drawable.tree
    };
    int currentImageId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        final ImageView show=(ImageView)findViewById(R.id.show);
        final Handler myHandler=new Handler(){

            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0x1233){
                    show.setImageResource(imageIds[currentImageId++%imageIds.length]);
                }
            }
        };
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                myHandler.sendEmptyMessage(0x1233);
            }
        },0,1200);
    }
}
