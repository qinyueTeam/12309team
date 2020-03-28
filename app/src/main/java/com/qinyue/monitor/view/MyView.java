package com.qinyue.monitor.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.blankj.utilcode.util.ConvertUtils;
import com.qinyue.monitor.R;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import static android.os.Build.VERSION_CODES.P;


/**
 * 创建人:qinyue
 * 创建日期:2020/3/29
 * 描述:
 **/
public class MyView extends View {
    private String yue = "";
    private String day = "";
    //定义画笔
    private Paint mPaint = new Paint();
    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    private void init(){
        mPaint.setTextSize(35);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.home_color));
        //获取绘制的View的宽度 ConvertUtils.dp2px(35)
        int width = getWidth();
        //获取绘制的View的高度
        int height = getHeight();
        canvas.drawText(yue+"月",ConvertUtils.dp2px(7),height/2-ConvertUtils.dp2px(2),mPaint);
//        canvas.drawLine(height-ConvertUtils.dp2px(5),ConvertUtils.dp2px(5),width-ConvertUtils.dp2px(5),ConvertUtils.dp2px(5),mPaint);
        canvas.drawLine(ConvertUtils.dp2px(15),height-ConvertUtils.dp2px(15),width-ConvertUtils.dp2px(22),ConvertUtils.dp2px(22),mPaint);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.text_color_blue));
        canvas .drawText(day+"日",width/2,height/2+ConvertUtils.dp2px(15),mPaint);
    }
    public void setTime(String yue,String day){
        this.yue = yue;
        this.day = day;
        invalidate();
    }
}
