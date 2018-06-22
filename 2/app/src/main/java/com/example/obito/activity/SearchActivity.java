package com.example.obito.activity;

import android.content.Intent;
import android.os.CancellationSignal;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.obito.R;
import com.example.obito.fragment.HotSearchTopicFragment;
import com.example.obito.fragment.NewsFragment;

public class SearchActivity extends BaseActivity {

    private String jsonData;

    private Button cancleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent=getIntent();
        jsonData=intent.getStringExtra(NewsFragment.JsonData);
        cancleButton=(Button)findViewById(R.id.cancelButton);
        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        replaceFragment(new HotSearchTopicFragment());
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.replaceFrameLayout,fragment);
        transaction.commit();
    }
}
