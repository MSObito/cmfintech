package com.example.obito.fifthweek.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.obito.fifthweek.CustomControl.TopView;
import com.example.obito.fifthweek.R;

public class CustomActivity extends AppCompatActivity {

    private TopView topView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        topView=(TopView)findViewById(R.id.top_view);
        topView.setOnClickLeft(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        topView.setRightTitle("设置");
        topView.setTitle("首页");
    }
}
