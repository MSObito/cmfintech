package com.example.obito.fifthweek.Activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.obito.fifthweek.R;
import com.example.obito.fifthweek.Service.MyIntentService;
import com.example.obito.fifthweek.Service.MyService;

public class MainActivity extends AppCompatActivity {

    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder=(MyService.DownloadBinder)service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button startService=(Button)findViewById(R.id.start_service);
        final Button stopService=(Button)findViewById(R.id.stop_service);
        final Button bindService=(Button)findViewById(R.id.bind_service);
        final Button unbindService=(Button)findViewById(R.id.unbind_service);
        Button startIntentService=(Button)findViewById(R.id.start_intent_service);
        Button downloadButton=(Button)findViewById(R.id.download_activity);
        Button customButton=(Button)findViewById(R.id.custom_activity);
        Button viewlistButton=(Button)findViewById(R.id.viewlist_activity);
        viewlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ViewListActivity.class);
                startActivity(intent);
            }
        });
        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CustomActivity.class);
                startActivity(intent);
            }
        });
        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent=new Intent(MainActivity.this, MyService.class);
                startService(startIntent);
            }
        });
        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stopIntent=new Intent(MainActivity.this,MyService.class);
                stopService(stopIntent);
            }
        });
        bindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bindIntent=new Intent(MainActivity.this,MyService.class);
                bindService(bindIntent,connection,BIND_AUTO_CREATE);
            }
        });
        unbindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(connection);
            }
        });
        startIntentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity","Thread id is"+ Thread.currentThread().getId());
                Intent intentService=new Intent(MainActivity.this, MyIntentService.class);
                startService(intentService);
            }
        });
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DownloadActivity.class);
                startActivity(intent);
            }
        });
    }
}
