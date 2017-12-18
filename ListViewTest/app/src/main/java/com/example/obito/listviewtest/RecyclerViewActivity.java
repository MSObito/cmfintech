package com.example.obito.listviewtest;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.example.obito.model.FruitModel;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewActivity extends AppCompatActivity {

    private List<Fruit> fruitList=new ArrayList<>();

    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView recyclerView;

    FruitAdapterRecycler adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_layout);

        FruitModel fruitModel=new FruitModel();
        fruitList=fruitModel.GetFruitList();
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        mSwipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        //StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new FruitAdapterRecycler(fruitList);
        recyclerView.setAdapter(adapter);
        initListener();
    }

    private void initListener() {

        initPullRefresh();

        initLoadMoreListener();

    }

    private void initPullRefresh() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<Fruit> headDatas = new ArrayList<Fruit>();
                        for (int i = 0; i <10 ; i++) {
                            Fruit fruit=new Fruit("addApple",R.drawable.apple_pic);
                            headDatas.add(fruit);
                        }
                        adapter.AddHeaderItem(headDatas);

                        //刷新完成
                        mSwipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(RecyclerViewActivity.this, "更新了 "+headDatas.size()+" 条目数据", Toast.LENGTH_SHORT).show();
                    }

                }, 3000);

            }
        });
    }

    private void initLoadMoreListener() {

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem ;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //判断RecyclerView的状态 是空闲时，同时，是最后一个可见的ITEM时才加载
                if(newState==RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItem+1==adapter.getItemCount()){

                    //设置正在加载更多
                    adapter.changeMoreStatus(adapter.LOADING_MORE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            List<Fruit> footerDatas = new ArrayList<Fruit>();
                            for (int i = 0; i< 10; i++) {
                                Fruit fruit=new Fruit("addBanana",R.drawable.banana_pic);
                                footerDatas.add(fruit);
                            }
                            adapter.AddFooterItem(footerDatas);
                            //设置回到上拉加载更多
                            adapter.changeMoreStatus(adapter.PULLUP_LOAD_MORE);

                            Toast.makeText(RecyclerViewActivity.this, "更新了 "+footerDatas.size()+" 条目数据", Toast.LENGTH_SHORT).show();
                        }
                    }, 3000);


                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //最后一个可见的ITEM
                lastVisibleItem=layoutManager.findLastVisibleItemPosition();
            }
        });

    }


}
