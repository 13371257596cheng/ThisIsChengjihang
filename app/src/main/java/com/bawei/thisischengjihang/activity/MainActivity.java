package com.bawei.thisischengjihang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawei.thisischengjihang.R;
import com.bawei.thisischengjihang.base.BaseActivity;
import com.bawei.thisischengjihang.base.BasePreacter;
import com.bawei.thisischengjihang.conterct.Conterct;
import com.bawei.thisischengjihang.conterct.CustomViewGroup;
import com.bawei.thisischengjihang.conterct.FlowLayout;
import com.bawei.thisischengjihang.preancter.Preancter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements Conterct.mView {
    List<String> stringList = new ArrayList<>();
    private CustomViewGroup customViewGroup;
    private FlowLayout mfl;

    @Override
    protected BasePreacter initPresenter() {
        return new Preancter(this);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void iniView() {
        mfl = findViewById(R.id.fl);
        customViewGroup = findViewById(R.id.cvg);
        customViewGroup.setOnSearchClick(new CustomViewGroup.OnSearchClick() {

            private Preancter preancter;

            @Override
            public void onSearch(String str) {
          /*  String url="http://mobile.bwstudent.com/small/commodity/v1/findCommodityByKeyword";
            url = url + "?keyword=" + str +"&page=1&count=5";
            preancter = new Preancter(MainActivity.this);
            preancter.onSuccess(url);*/

                stringList.add(str);
                mfl.removeAllViews();
                for(String string : stringList){
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(10, 5, 10, 5);

                    TextView textView = new TextView(MainActivity.this);
                    textView.setPadding(20, 10, 20, 10);
                    textView.setText(string);
                    textView.setLayoutParams(layoutParams);
                    mfl.addView(textView, layoutParams);
                }
            }
        });

    }

    @Override
    protected void getData() {

    }

    @Override
    public void onSuccess(String str) {
        Log.i("CustomActivity", "onSuccess: " + str);
    }

    @Override
    public void onFiaulse(String str) {
        Log.i("CustomActivity", "onFailure: " + str);
    }

}
