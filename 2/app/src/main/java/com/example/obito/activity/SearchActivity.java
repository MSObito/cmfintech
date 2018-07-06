package com.example.obito.activity;

import android.content.Intent;
import android.os.CancellationSignal;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.obito.R;
import com.example.obito.fragment.HotSearchTopicFragment;
import com.example.obito.fragment.NewsFragment;
import com.example.obito.fragment.NoResultFragment;
import com.example.obito.fragment.ResultsFragment;
import com.example.obito.model.News;
import com.example.obito.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity {

    private String jsonData;
    private static List<News> results=new ArrayList<>();
    private List<News> newsList;

    private Button cancleButton;
    private EditText searchEditText;
    private ImageView deleteImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent=getIntent();
        //jsonData=intent.getStringExtra(NewsFragment.JsonData);
        String whichFragment=intent.getStringExtra(NewsFragment.JsonData);
        switch (whichFragment){
            case "NewsFragment":
                jsonData=intent.getStringExtra(NewsFragment.NEWSDATA);
                newsList=JsonUtil.parseJSONWithGson(jsonData, News.class);
                break;
            case "OrderFragment":
                newsList=(List<News>)intent.getSerializableExtra(NewsFragment.JsonData);
                break;
                default:
                    break;
        }
        cancleButton=(Button)findViewById(R.id.cancelButton);
        searchEditText=(EditText)findViewById(R.id.searchView);
        deleteImageView=(ImageView)findViewById(R.id.searchView_delete);
        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchEditText.setText("");
                deleteImageView.setVisibility(View.GONE);
                replaceFragment(new HotSearchTopicFragment());
            }
        });
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!"".equals(charSequence)){
                    deleteImageView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                CharSequence topic=searchEditText.getText();
                if(results.size()!=0){
                    results.clear();
                }
                for (News news:newsList) {
                    if(news.getTitile().contains(topic)){
                        results.add(news);
                    }
                }
                if(results.size()==0) {
                    replaceFragment(new NoResultFragment());
                }else {
                    ResultsFragment resultsFragment=new ResultsFragment();
                    resultsFragment.setNewsList(results);
                    replaceFragment(resultsFragment);
                }
            }
        });
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    CharSequence topic=searchEditText.getText();
//                    String jsonData=JsonUtil.getJson("newsList.json",SearchActivity.this);
//                    List<News> newsList=JsonUtil.parseJSONWithGson(jsonData, News.class);
                    if(results.size()!=0){
                        results.clear();
                    }
                    for (News news:newsList) {
                        if(news.getTitile().contains(topic)){
                            results.add(news);
                        }
                    }
                    if(results.size()==0) {
                        replaceFragment(new NoResultFragment());
                    }else {
                        ResultsFragment resultsFragment=new ResultsFragment();
                        resultsFragment.setNewsList(results);
                        replaceFragment(resultsFragment);
                    }
                }
                return true;
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
