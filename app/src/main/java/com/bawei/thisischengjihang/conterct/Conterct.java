package com.bawei.thisischengjihang.conterct;

import com.bawei.thisischengjihang.base.IBaseView;

public interface Conterct {
    interface mView extends IBaseView {
        void onSuccess(String str);
        void onFiaulse(String str);
    }
    interface mPreacter{
        void onSuccess(String url);
    }
    interface mModel{
        void onSuccess(String url,onGetSuccess onGetSuccess);
        interface onGetSuccess{
            void onSuccess(String str);
            void onFiaulse(String str);
        }
    }
}
