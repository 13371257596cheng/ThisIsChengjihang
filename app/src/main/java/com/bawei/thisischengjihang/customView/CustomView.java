package com.bawei.thisischengjihang.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CustomView extends View {

    private Paint mPaint;
    private int[] mArray;
    private int mTotalCount;

    public CustomView(Context context) {
        super(context);
        init();
    }
    public CustomView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        Log.i("dj", "add 2 ");
        init();
    }

    public CustomView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(){
        //初始化画笔的操作
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }


    public void setData(int[] array){
        if(array != null && array.length > 0) {
            mArray = array;

            for(int i: array){
                mTotalCount = i;
            }
        }
    }

    /**
     * 用来接收系统测量宽高的结果，并作出修改
     * @param widthMeasureSpec 系统测量出来的宽度总集
     *                         widthMeasureSpec是个32位int类型，
     *                         其中前两位是测量模式，
     *                         后30位是具体的值
     * @param heightMeasureSpec 系统高量出来的宽度总集
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    /**
     * 接收系统测量位置的结果，注意：安卓所有的控件，默认都是矩形
     * @param changed 告诉我们控件的位置是否改变
     * @param left 告诉我们控件的最左边在x的位置
     * @param top 控件最上面在y轴的位置
     * @param right 右边在x轴位置
     * @param bottom 下面在y轴的位置
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * 画
     * @param canvas 一块白色的画布，随便我们蹂躏他
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//
//        //画圆，
//        // 第一个参数，圆心在x轴的位置
//        // 第二个参数，圆心在y轴的位置
//        // 第三个参数，半径
//        // 第四个参数，画笔
//        canvas.drawCircle(100, 100, 50, mPaint);
//
//        mPaint.setColor(Color.GREEN);
//
//        Rect rect = new Rect();
//        rect.left = 200;
//        rect.right = 400;
//        rect.top = 200;
//        rect.bottom = 400;
//        canvas.drawRect(rect, mPaint);

        if(mArray == null || mArray.length == 0){
            return;
        }


        //动态的饼图
        //饼图所在位置
        RectF rectF = new RectF();
        rectF.left = 400;
        rectF.right = 600;
        rectF.top = 200;
        rectF.bottom = 400;

        //起始角度，用来保存当前画的起始角度位置
        float startAngle = 0;

        for(int i = 0 ; i < mArray.length; i++) {
            //通过当前 数据值 除以 总值 乘以 360 计算出旋转角度
            float sweepAngle = ((float)mArray[i]) / mTotalCount * 360;

            //通过余数获取颜色
            int[] mColorArray = new int[]{Color.RED, Color.BLUE, Color.GREEN, Color.GRAY, Color.BLACK};
            mPaint.setColor(mColorArray[i % mColorArray.length]);

            canvas.drawArc(rectF, startAngle,
                    sweepAngle,
                    true, mPaint);

            //起始角度 + 旋转角度 = 下一个扇形的起始角度
            startAngle += sweepAngle;
        }

    }
}
