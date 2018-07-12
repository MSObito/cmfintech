package com.example.obito.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.obito.R;
import com.example.obito.adapter.FragmentAdapter;
import com.example.obito.adapter.NewsAdapter;
import com.example.obito.entity.TabEntity;
import com.example.obito.fragment.CollectFragment;
import com.example.obito.fragment.HistoryFragment;
import com.example.obito.model.Image;
import com.example.obito.model.News;
import com.example.obito.utils.SdCardManager;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

public class CollectHistoryActivity extends AppCompatActivity {

    private ImageView backImageView;
    private TextView editTextView;
    private TextView chooseAllTextView;
    private TextView deleteTextView;
    private ViewPager viewPager;
    private CommonTabLayout commonTabLayout;
    private FrameLayout commandLayout;

    private List<Fragment> fragmentList=new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"收藏", "历史"};
    private CollectFragment collectFragment;
    private HistoryFragment historyFragment;

    private List<Boolean> checked;
    private List<News> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_history);
        backImageView=(ImageView)findViewById(R.id.backImageView);
        commandLayout=(FrameLayout)findViewById(R.id.commandBar);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        editTextView=(TextView)findViewById(R.id.editTextView);
        chooseAllTextView=(TextView)findViewById(R.id.chooseTextView);
        deleteTextView=(TextView)findViewById(R.id.deleteTextView);
        viewPager=(ViewPager)findViewById(R.id.collectHistoryViewPager);
        commonTabLayout=(CommonTabLayout)findViewById(R.id.commonTabLayout);
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i]));
        }
        init();
        collectFragment=new CollectFragment();
        historyFragment=new HistoryFragment();
        fragmentList.add(collectFragment);
        fragmentList.add(historyFragment);
        editTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextView.getText().equals("编辑")){
                    editTextView.setText("取消");
                    commandLayout.setVisibility(View.VISIBLE);
                    collectFragment.getNewsAdapter().setShow(true);
                    collectFragment.getNewsAdapter().notifyDataSetChanged();
                }else{
                    editTextView.setText("编辑");
                    commandLayout.setVisibility(View.GONE);
                    collectFragment.getNewsAdapter().setShow(false);
                    collectFragment.getNewsAdapter().notifyDataSetChanged();
                }
            }
        });
        chooseAllTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checked=collectFragment.getIsChecked();
                for(int i=0;i<checked.size();i++){
                    checked.set(i,true);
                }
                collectFragment.getNewsAdapter().notifyDataSetChanged();
            }
        });
        deleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newsList=collectFragment.getNewsList();
                for(int i=checked.size()-1;i>=0;i--){
                    if(checked.get(i)){
                        newsList.remove(i);
                        checked.remove(i);
                        collectFragment.getNewsAdapter().notifyItemRemoved(i);
                        collectFragment.getNewsAdapter().notifyItemRangeChanged(0,newsList.size());
                        SdCardManager.deleteFile(view.getContext(),"collect",newsList.get(i).getId());
                    }
                }
            }
        });
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),fragmentList));
        checked=collectFragment.getIsChecked();
        newsList=collectFragment.getNewsList();
    }

    private void init() {
        commonTabLayout.setTabData(mTabEntities);
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                commonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setCurrentItem(1);
    }
}
