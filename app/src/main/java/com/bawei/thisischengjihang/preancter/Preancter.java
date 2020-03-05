package com.bawei.thisischengjihang.preancter;

import com.bawei.thisischengjihang.base.BasePreacter;
import com.bawei.thisischengjihang.base.IBaseView;
import com.bawei.thisischengjihang.conterct.Conterct;
import com.bawei.thisischengjihang.model.HOImodel;

public class Preancter extends BasePreacter implements Conterct.mPreacter {
    private HOImodel hoiModel;

    public Preancter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    public void onSuccess(String url) {
        hoiModel.onSuccess(url, new Conterct.mModel.onGetSuccess() {
            @Override
            public void onSuccess(String str) {
                IBaseView view = getView();
                if (view instanceof Conterct.mView){
                    Conterct.mView view1= (Conterct.mView) view;
                    view1.onSuccess(str);
                }
            }

            @Override
            public void onFiaulse(String str) {
                IBaseView view = getView();
                if (view instanceof Conterct.mView){
                    Conterct.mView view1= (Conterct.mView) view;
                    view1.onFiaulse(str);
                }
            }
        });
    }

    @Override
    protected void iniModel() {
        hoiModel = new HOImodel();
    }
}
