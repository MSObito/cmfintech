package com.example.obito.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.obito.R;
import com.example.obito.adapter.MessageAdapter;
import com.example.obito.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView messageRecyclerView;

    private MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        toolbar=(Toolbar)findViewById(R.id.backToolbar);
        messageRecyclerView=(RecyclerView)findViewById(R.id.messageRecyclerView);
        setSupportActionBar(toolbar);
        ActionBar actionBar =  getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        messageRecyclerView.setLayoutManager(layoutManager);
        messageAdapter=new MessageAdapter(initData());
        messageRecyclerView.setAdapter(messageAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    private List<Message> initData(){
        List<Message> messageList=new ArrayList<>();
        Message message=new Message();
        message.setName("蓝小穹");
        message.setDate("11-20");
        message.setMessage("欢迎使用蓝穹APP阅读时事金融新闻，一手掌握最新金融资讯尽在蓝穹APP！");
        Message message1=new Message();
        message1.setName("蓝小穹");
        message1.setDate("11-18");
        message1.setMessage("蓝穹APP已经陪伴你1325天了，感谢有你在的日子，今后将陪伴你走过更多的日子哦。");
        messageList.add(message);
        messageList.add(message1);
        return messageList;
    }
}
