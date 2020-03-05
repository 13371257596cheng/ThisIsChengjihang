package com.bawei.thisischengjihang.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.thisischengjihang.DrawCircle;
import com.bawei.thisischengjihang.R;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv;
    private TextView tv;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private DrawCircle dc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        iv = findViewById(R.id.img);
        tv = findViewById(R.id.tv);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        dc = findViewById(R.id.dc);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b1:
                ObjectAnimator animator = ObjectAnimator.ofFloat(iv,"translationX",0,500f);
                animator.setDuration(5000);
                animator.start();
                break;
            case R.id.b2:
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(iv,"rotation",0,360f);
                animator1.setDuration(5000);
                animator1.start();
                break;
            case R.id.b3:
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(iv,"scaleX",1f,0.3f);
                animator2.setDuration(5000);
                animator2.start();
                break;
            case R.id.b4:
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(iv,"alpha",1f,0.3f);
                animator3.setDuration(5000);
                animator3.start();
                break;
            case R.id.b5:
                ObjectAnimator an1 = ObjectAnimator.ofFloat(iv,"translationX",0,500f);
                an1.setDuration(5000);
                ObjectAnimator an2 = ObjectAnimator.ofFloat(iv,"rotation",0,360f);
                an2.setDuration(5000);
                ObjectAnimator an3 = ObjectAnimator.ofFloat(iv,"scaleX",1f,0.3f);
                an3.setDuration(5000);
                ObjectAnimator an4 = ObjectAnimator.ofFloat(iv,"alpha",1f,0.3f);
                an4.setDuration(5000);

                ObjectAnimator an5 = ObjectAnimator.ofFloat(iv,"translationX",500f,0);
                an5.setDuration(5000);
                ObjectAnimator an6 = ObjectAnimator.ofFloat(iv,"alpha",0.3f,1f);
                an6.setDuration(5000);
                ObjectAnimator an7 = ObjectAnimator.ofFloat(iv,"scaleX",0.3f,1f);
                an7.setDuration(5000);

                AnimatorSet set = new AnimatorSet();
                set.play(an1).with(an2).with(an3).with(an4).before(an5).before(an6).before(an7);
                set.setDuration(5000);
                set.start();
                break;
            case R.id.img:
                textAction();
                break;
            case R.id.tv:
                tvAction();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void tvAction() {
        ValueAnimator animator = ValueAnimator.ofInt(1,360);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int fl = (int) animation.getAnimatedValue();
                dc.setData(fl);
                Log.i("xxx",fl+"");
                dc.invalidate();
            }
        });
        ValueAnimator animator1 = ValueAnimator.ofArgb(0xffff0000,0xff00ff00);
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int clor = (int) animation.getAnimatedValue();
                dc.setcolor(clor);
                dc.invalidate();
            }
        });
        AnimatorSet set = new AnimatorSet();
        set.play(animator).with(animator1);
        set.setDuration(10000);
        set.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void textAction() {
        ValueAnimator animator = ValueAnimator.ofFloat(0,1f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                tv.setText(value*100+"%");
            }
        });
        ValueAnimator animator1 = ValueAnimator.ofArgb(0xffff0000,0xff00ff00);
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int clor = (int) animation.getAnimatedValue();
                tv.setTextColor(clor);
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.play(animator).with(animator1);
        set.setDuration(5000);
        set.start();
    }
}
