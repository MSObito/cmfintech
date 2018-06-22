package com.example.obito.fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.obito.R;
import com.example.obito.activity.ErrorActivity;
import com.example.obito.activity.SearchActivity;
import com.example.obito.adapter.NewsAdapter;
import com.example.obito.model.News;
import com.example.obito.utils.JsonUtil;
import com.jcodecraeer.xrecyclerview.ArrowRefreshHeader;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class NewsFragment extends Fragment {

    private Toolbar toolbar;

    private List<News> newsList=new ArrayList<>();

    private int num=0;
    private String jsonData;

    private XRecyclerView xrecyclerView;
    private NewsAdapter adapter;

    public static final String JsonData="JsonData";

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        if(menu!=null){
            if(menu.getClass()== MenuBuilder.class){
                try{
                    Method m=menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu,true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
//                Toast.makeText(getActivity(),"You clicked search in news fragment", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),SearchActivity.class);
                intent.putExtra(JsonData,jsonData);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        if (getChildFragmentManager().getBackStackEntryCount() == 0) {
            inflater.inflate(R.menu.search, menu);
        }

//        getMenuInflater().inflate(R.menu.search,menu);
//        return true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.news_fragment,container,false);
        toolbar=(Toolbar)view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar=((AppCompatActivity) getActivity()).getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setHasOptionsMenu(true);
        xrecyclerView=(XRecyclerView) view.findViewById(R.id.news_xRecyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        xrecyclerView.setLayoutManager(layoutManager);
        xrecyclerView.setRefreshProgressStyle(ProgressStyle.BallZigZag);
        xrecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallZigZag);
        xrecyclerView.setLoadingMoreEnabled(true);
        xrecyclerView.setPullRefreshEnabled(true);
        try{
            jsonData=JsonUtil.getJson("newsList.json",getContext());
            newsList=sortTop(JsonUtil.parseJSONWithGson(jsonData,News.class,num));  //上拉加载添加数据
        }catch (Exception e){
            e.printStackTrace();
            Intent intent=new Intent(getActivity(), ErrorActivity.class);
            startActivity(intent);
        }

        adapter=new NewsAdapter(newsList);
        xrecyclerView.setAdapter(adapter);

        /**
         *设定下拉刷新和上拉加载监听
         */
        xrecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            //上拉加载监听
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        addData();
                        xrecyclerView.loadMoreComplete();
                        adapter.notifyDataSetChanged();
                    }
                }, 1000);
            };
            //下拉刷新监听
            @Override
            public void onRefresh() {
                        initData();
                        xrecyclerView.refreshComplete();
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getContext(),"更新了"+ newsList.size()+"条数据", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    /**
     *上拉加载添加数据
     */
    private void addData() {
        List<News> addNewsList= new ArrayList<>();
        addNewsList=JsonUtil.parseJSONWithGson(jsonData,News.class,num+5);  //上拉加载添加数据
        for (News news:addNewsList) {
            newsList.add(news);
        }
        adapter.notifyDataSetChanged();
    }

    /**
     *初始化数据
     */
    private void initData() {
        newsList.clear();
        List<News> newsList1=new ArrayList<>();
        newsList1=JsonUtil.parseJSONWithGson(jsonData,News.class);
        for (News news:newsList1) {
            newsList.add(news);
        }
    }

    private List<News> sortTop(List<News> newsList){
        List<News> newsList1=new ArrayList<>();
        List<News> unTopList=new ArrayList<>();
        for (News news:newsList) {
            if(news.getTop()){
                newsList1.add(news);
            }else{
                unTopList.add(news);
            }
        }
        for (News news:unTopList) {
            newsList1.add(news);
        }
        return newsList1;
    }
}
