package com.bawei.thisischengjihang;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Time: 2020/3/4
 * Author: 时文豪
 * Description:
 */
public class DrawCircle extends View {
    private Paint paint;
    private int dat;
    private int color;

    public DrawCircle(Context context) {
        super(context);
        init();
    }
    public DrawCircle(Context context,  AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    public void setData(int data){
        this.dat = data;
    }
    public void setcolor(int color){
        this.color = color;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int sta = 0;
        int sw = dat;
        sta +=sw/400;
        RectF rectF = new RectF();
        rectF.left = 300;
        rectF.right = 500;
        rectF.top = 100;
        rectF.bottom = 300;
        paint.setColor(color);
        Log.i("xxx",sta+"  "+sw);
        canvas.drawArc(rectF,sta,sw,true,paint);
    }
}
