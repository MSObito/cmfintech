package com.example.obito.fifthweek.Activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.obito.fifthweek.Adapter.ViewPagerAdapter;
import com.example.obito.fifthweek.CustomControl.MyViewPager;
import com.example.obito.fifthweek.R;

import java.util.ArrayList;
import java.util.List;

public class ViewListActivity extends AppCompatActivity {

    private ListView listview=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        initHeadView();
    }

    private void initHeadView()
    {
        listview = (ListView)this.findViewById(R.id.listView1);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_viewpager, null);
        MyViewPager viewpager = (MyViewPager) view.findViewById(R.id.head_viewpager);
        List<ImageView> listtemp = new ArrayList<ImageView>();
        for(int i = 0;i<4;i++)
        {
            ImageView img = new ImageView(this);
            img.setLayoutParams(new ViewGroup.LayoutParams(ViewPager.LayoutParams.WRAP_CONTENT,100));
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            img.setBackgroundResource(R.mipmap.ic_launcher);
            listtemp.add(img);
        }
        ViewPagerAdapter viewadapter = new ViewPagerAdapter(listtemp);
        listview.addHeaderView(view);
        String[] data=new String[12];
        for(int i=0;i<12;i++){
            data[i]=String.valueOf(i);
        }
        listview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data));
        viewpager.setAdapter(viewadapter);
    }
}
