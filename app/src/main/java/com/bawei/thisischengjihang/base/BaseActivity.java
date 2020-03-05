package com.bawei.thisischengjihang.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity <P extends BasePreacter>extends AppCompatActivity {
    P mPreantction;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        mPreantction=initPresenter();
        iniView();
        getData();
    }
    public P getmPreantction(){
        return mPreantction;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPreantction!=null){
            mPreantction.datachView();
            mPreantction=null;
        }
    }

    protected abstract P initPresenter();

    protected abstract int getLayoutID();

    protected abstract void iniView();

    protected abstract void getData();

}
