package com.example.obito.activitytext;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends BaseActivity {

    private TextView textView;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button_normal;
    private Button button_dialog;
    private Button button_standard;
    private Button button_fragment;
    private Button button_news;
    private static final String TAG="FirstActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("FirstActivity","Task id is "+getTaskId());
        Log.d(TAG,"onCreate");
        Log.d(TAG,this.toString());
        setContentView(R.layout.first_layout);
        getSaveInstanceState(savedInstanceState);
        getAllControls();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(FirstActivity.this,"You Clicked Button 1", Toast.LENGTH_SHORT).show();
                //finish();
//                Intent intent=new Intent("com.example.activitytest.ACTION_START");
//                intent.addCategory("com.example.activitytest.MY_CATEGORY");
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(FirstActivity.this,SecondActivity.class);
//                String data="Hello SecondActivity,i'm firstActivity.i give you some data";
//                intent.putExtra("extra_data",data);
//                startActivity(intent);
                SecondActivity.actionStart(FirstActivity.this,"Hello SecondActivity","i'm firstActivity.i give you some data");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstActivity.this,SecondActivity.class);
                startActivityForResult(intent,1);
            }
        });
        button_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstActivity.this,DialogActivity.class);
                startActivity(intent);
            }
        });
        button_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstActivity.this,NormalActivity.class);
                startActivity(intent);
            }
        });
        button_standard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstActivity.this,FirstActivity.class);
                startActivity(intent);
            }
        });
        button_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstActivity.this, FragmentActivity.class);
                startActivity(intent);
            }
        });
        button_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstActivity.this,NewsDemoActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 保存FirstActivity的一些临时数据，防止被系统回收
     * @param outState 键值对存放的数据对象
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData="Something you just typed";
        outState.putString(TAG,tempData);
    }

    private void getSaveInstanceState(Bundle savedInstanceState){
        if(savedInstanceState!=null){
            String tempData=savedInstanceState.getString(TAG);
            Log.d(TAG,tempData);
        }
    }

    /**
     * 获取first_layout中所有控件
     */
    private void getAllControls(){
        button1=(Button) findViewById(R.id.button_1);
        button2=(Button)findViewById((R.id.button_data));
        button3=(Button)findViewById(R.id.button_data_return);
        textView=(TextView)findViewById(R.id.textview_result);
        button_dialog=(Button)findViewById(R.id.start_dialog_activity);
        button_normal=(Button)findViewById(R.id.start_normal_activity);
        button_standard=(Button)findViewById(R.id.button_standard);
        button_fragment=(Button)findViewById(R.id.button_fragment);
        button_news=(Button)findViewById(R.id.button_news);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    String returnData=data.getStringExtra("data_return");
                    textView.setText(returnData);
                    Log.d("FirstActivity",returnData);
                }
                default:
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(FirstActivity.this,"You Clicked Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(FirstActivity.this,"You Clicked Remove", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart");
    }
}
