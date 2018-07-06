package com.example.obito.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.obito.R;
import com.example.obito.adapter.ImageAdapter;
import com.example.obito.model.NewsBean;

import java.util.ArrayList;
import java.util.List;

public class ImagesActivity extends BaseActivity {

    private List<Bitmap> imageViewList;
    private int position;

    private ViewPager viewPager;
    private ImageAdapter imageAdapter;
    private TextView whichTextView;
    private ImageView saveImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        viewPager=(ViewPager)findViewById(R.id.imagesViewpager);
        whichTextView=(TextView) findViewById(R.id.whichTextView);
        saveImageView=(ImageView)findViewById(R.id.saveImageView);
        Intent intent=getIntent();
        imageViewList=(List<Bitmap>)intent.getSerializableExtra(NewsActivity.IMAGES_LIST);
        position=intent.getIntExtra(NewsActivity.POSITION,0);
        imageAdapter=new ImageAdapter(this,imageViewList);
        viewPager.setAdapter(imageAdapter);
        viewPager.setCurrentItem(position);
        whichTextView.setText(position+1+"/"+imageViewList.size());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                whichTextView.setText(position + 1 + "/" + imageViewList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        saveImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"保存成功",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
