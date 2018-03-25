package com.example.obito.fifthweek.CustomControl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.obito.fifthweek.R;

import org.w3c.dom.Text;

/**
 * Created by obito on 2018/3/21.
 */

public class TopView extends RelativeLayout {

    //返回按钮
    private ImageView top_left;

    //标题
    private TextView top_title;

    private TextView top_right;

    public TopView(Context context) {
        super(context);
    }

    public TopView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        LayoutInflater.from(context).inflate(R.layout.view_top,this);
        top_left=(ImageView)findViewById(R.id.top_left);
        top_title=(TextView)findViewById(R.id.top_title);
        top_right=(TextView)findViewById(R.id.top_right);
    }

    //返回按钮点击自定义事件
    public void setOnClickLeft(OnClickListener listener){
        top_left.setOnClickListener(listener);
    }

    //设置中间标题
    public void setTitle(String title){
        top_title.setText(title);
    }

    //设置右标题
    public void setRightTitle(String title){
        top_right.setText(title);
    }
}
