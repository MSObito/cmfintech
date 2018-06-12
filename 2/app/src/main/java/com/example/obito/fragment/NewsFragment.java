package com.example.obito.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.obito.adapter.NewsAdapter;
import com.example.obito.model.News;
import com.example.obito.utils.JsonUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {

    private Toolbar toolbar;

    private List<News> newsList;

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
                Toast.makeText(getActivity(),"You clicked search in news fragment", Toast.LENGTH_SHORT).show();
                break;
            default:
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
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.news_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        String jsonData=JsonUtil.getJson("newsList.json",getContext());
        newsList=JsonUtil.parseJSONWithGson(jsonData);
        NewsAdapter adapter=new NewsAdapter(newsList);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
