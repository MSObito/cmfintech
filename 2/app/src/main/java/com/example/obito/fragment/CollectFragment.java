package com.example.obito.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.obito.R;
import com.example.obito.adapter.NewsAdapter;
import com.example.obito.model.News;
import com.example.obito.model.NewsBean;
import com.example.obito.utils.SdCardManager;

import java.util.ArrayList;
import java.util.List;

public class CollectFragment extends Fragment {

    private RecyclerView collectRecyclerView;

    private List<News> newsList=new ArrayList<>();
    private NewsAdapter newsAdapter;
    private List<Boolean> isChecked=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_collect,container,false);
        collectRecyclerView=(RecyclerView)view.findViewById(R.id.collectRecyclerView);
        List<Object> objectList=SdCardManager.getAllFiles(getContext(),"collect");
        for (Object o: objectList) {
            newsList.add((News)o);
        }
        for(int i=0;i<newsList.size();i++){
            isChecked.add(false);
        }
        newsAdapter=new NewsAdapter(newsList,getActivity(),isChecked);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        collectRecyclerView.setLayoutManager(layoutManager);
        collectRecyclerView.setAdapter(newsAdapter);
        return view;
    }

    public NewsAdapter getNewsAdapter() {
        return newsAdapter;
    }

    public List<Boolean> getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(List<Boolean> isChecked) {
        this.isChecked = isChecked;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
}
