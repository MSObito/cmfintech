package com.example.obito.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.obito.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ImageAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<String> imagesList;
    private SparseArray<View> cacheView;
    private ViewGroup containerTemp;

    public ImageAdapter(Context context, ArrayList<String> imagesList) {
        this.imagesList = imagesList;
        this.context=context;
        cacheView=new SparseArray<>(imagesList.size());
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if(container==null)
            containerTemp=container;
        View view=cacheView.get(position);
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.image_item,container,false);
            view.setTag(position);
            PhotoView photoView=(PhotoView)view.findViewById(R.id.photoView);
            PhotoViewAttacher photoViewAttacher=new PhotoViewAttacher(photoView);
            Picasso.get()
                    .load(imagesList.get(position))
                    .into(photoView);
            photoViewAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    Activity activity=(Activity)context;
                    activity.finish();
                }
            });
            cacheView.put(position,view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return imagesList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
