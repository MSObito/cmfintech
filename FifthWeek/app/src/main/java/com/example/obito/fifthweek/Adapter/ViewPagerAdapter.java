package com.example.obito.fifthweek.Adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.obito.fifthweek.CustomControl.MyViewPager;

import java.util.List;

/**
 * Created by obito on 2018/3/25.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private List<ImageView> list=null;

    public ViewPagerAdapter(List<ImageView> _list){
        list=_list;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((MyViewPager)container).removeView(list.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((MyViewPager)container).addView(list.get(position));
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==object);
    }
}
