package com.example.obito.fifthweek.CustomControl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by obito on 2018/3/21.
 */

public class CustomView extends View implements View.OnClickListener {

    //定义画笔
    private Paint mPaint;

    //用于获取文字的宽和高
    private Rect mRect;

    //计数值，每点击一次控件，增加1
    private int mCount=0;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        //初始画笔、Rect
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mRect=new Rect();
        //点击事件
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(50);
        String text=String.valueOf(mCount);
        mPaint.getTextBounds(text,0,text.length(),mRect);
        float textWidth= mRect.width();
        float textHeight=mRect.height();
        canvas.drawText("点了我"+text+"次",getWidth()/2-textWidth/2,getHeight()/2+textHeight/2,mPaint);
    }

    @Override
    public void onClick(View v) {
        mCount++;
        invalidate();
    }
}
