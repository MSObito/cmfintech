package com.example.obito.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.obito.R;
import com.example.obito.adapter.TopicsAdapter;

public class HotSearchTopicFragment extends Fragment {

    private String[] hotTopics={"招商仁和","科技时事","双十二纪念日","深圳时事","设计资讯"};

    private RecyclerView hotSearchRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.hot_search_topic_fragment,container,false);
        hotSearchRecyclerView=(RecyclerView)view.findViewById(R.id.hotSearchRecyclerView);
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),3);
        hotSearchRecyclerView.setLayoutManager(layoutManager);
        TopicsAdapter adapter=new TopicsAdapter(hotTopics);
        hotSearchRecyclerView.setAdapter(adapter);
        return view;
    }
}
