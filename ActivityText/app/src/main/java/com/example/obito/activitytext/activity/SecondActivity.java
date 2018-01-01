package com.example.obito.activitytext.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.obito.activitytext.R;

public class SecondActivity extends BaseActivity {

    private static final String TAG="SecondActivity";
    private static final String TAG1="SecondActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SecondActivity","Task id is "+getTaskId());
        setContentView(R.layout.second_layout);
        TextView textView=(TextView)findViewById(R.id.textview_1);
        Button button=(Button)findViewById(R.id.button_2);
        Button button2=(Button)findViewById(R.id.button_singletop);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("data_return","Hello firstActivity,sorry,i dislike your data");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        Intent intent=getIntent();
        if(intent.getStringExtra(TAG)!=null&&intent.getStringExtra(TAG1)!=null){
            String data=intent.getStringExtra(TAG);
            String data2=intent.getStringExtra(TAG1);
            textView.setText(data+data2);
            Log.d("SecondActivity",data);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    /**
     * 启动并向Second Activity传送数据
     * @param context 当前可见的Activity
     * @param data1 SecondActivity所需数据1
     * @param data2 SecondActivity所需数据2
     */
    public static void actionStart(Context context,String data1,String data2){
        Intent intent=new Intent(context,SecondActivity.class);
        intent.putExtra(TAG,data1);
        intent.putExtra(TAG1,data2);
        context.startActivity(intent);
    }
}
