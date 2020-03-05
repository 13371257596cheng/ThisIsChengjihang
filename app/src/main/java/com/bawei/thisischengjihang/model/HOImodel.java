package com.bawei.thisischengjihang.model;

import com.bawei.thisischengjihang.conterct.Conterct;
import com.bawei.thisischengjihang.uitil.NetUtilsVolley;

public class HOImodel implements Conterct.mModel {
    @Override
    public void onSuccess(String url, final onGetSuccess onGetSuccess) {
        NetUtilsVolley.getInstance().doGet(url, new NetUtilsVolley.Collack() {
            @Override
            public void onSuccess(String json) {
                onGetSuccess.onSuccess(json);
            }

            @Override
            public void onFialuse(String msg) {
                onGetSuccess.onFiaulse(msg);
            }
        });
    }
}
