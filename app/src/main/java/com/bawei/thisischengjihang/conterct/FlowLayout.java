package com.bawei.thisischengjihang.conterct;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bawei.thisischengjihang.R;

public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
//        String text = ta.getString(R.styleable.FlowLayout);
//
//        int mColor = ta.getColor(R.styleable.FlowLayout_fl_bg, Color.GRAY);
//        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

//        setBackgroundColor(mColor);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //让系统计算所有的孩子
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        //获取当前控件也就是ViewGroup的宽高
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        //控件总宽度 - 控件右内边距 = 控件实际可以使用宽度
        compare(width - getPaddingRight());
        //测量自己
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //根据孩子的数量进行遍历
        for(int i = 0; i < getChildCount(); i++){
            //根据角标，获取当前孩子
            View child = getChildAt(i);
            //获取标记
            Rect rect = (Rect) child.getTag();
            //根据标记，赋值
            child.layout(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    /**
     * 用来通过孩子的宽度配合控件的宽度以及使用的宽度，计算孩子的摆放位置
     * @param width 当前ViewGroup可以使用宽
     */
    private void compare(int width){
        //使用宽度，初始化是getPaddingLeft,也就是我们控件的左侧内边距
        int usedWidth = getPaddingLeft();

        //使用高度，初始化是getPaddingTop, 也就是我们控件的上边内边距
        int usedHeight = getPaddingTop();

        //根据孩子的数量进行遍历
        for(int i = 0; i < getChildCount(); i++){
            //根据角标，获取当前孩子
            View child = getChildAt(i);

            //拿到孩子的宽度，不包含外边距
            int childWidth = child.getMeasuredWidth();
            //拿到孩子的高度，不包含外边距
            int childHeight = child.getMeasuredHeight();

            //获取孩子的布局管理
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) child.getLayoutParams();

            //孩子真正占用的宽度 = 孩子测量的宽度 + 孩子左边外边距 + 孩子右边的外边距
            int childWidthReal = childWidth + layoutParams.leftMargin + layoutParams.rightMargin;

            //孩子真正占用的高度 = 孩子测量的高度 + 孩子上边外边距 + 孩子下边的外边距
            int childHeightReal = childHeight + layoutParams.topMargin + layoutParams.bottomMargin;

            //如果已经使用的 宽度 + 孩子真正占用的高度 > 屏幕宽度，说明需要换行
            if((usedWidth + childWidthReal) > width){
                //如果需要换行，使用过的宽度，肯定是控件的左侧内边距
                usedWidth = getPaddingLeft();
                //如果需要换行，使用过的高度，肯定以前的高度 + 孩子真正占用的高度
                usedHeight += childHeightReal;
            }

            //不管有没有换行，使用宽度都等于 原来使用的宽度 + 孩子真正的宽度
            usedWidth += childWidthReal;

            Rect rect = new Rect();
            rect.top = usedHeight;
            rect.bottom = usedHeight + childHeightReal;
            rect.left = usedWidth - childWidthReal;
            rect.right = usedWidth;

            //给孩子添加了标记
            child.setTag(rect);
        }
    }
    }

