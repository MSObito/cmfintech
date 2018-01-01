package com.example.obito.activitytext.activity;

import android.os.Process;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.obito.activitytext.ActivityCollector;
import com.example.obito.activitytext.R;
import com.example.obito.activitytext.activity.BaseActivity;

public class ThirdActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ThirdActivity","Task id is "+getTaskId());
        setContentView(R.layout.third_layout);
        Button button=(Button)findViewById(R.id.button_quit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();
                android.os.Process.killProcess(Process.myPid());
            }
        });
    }
}
