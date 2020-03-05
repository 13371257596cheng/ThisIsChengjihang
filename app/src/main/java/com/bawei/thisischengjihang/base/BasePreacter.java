package com.bawei.thisischengjihang.base;

import java.lang.ref.WeakReference;

public abstract class BasePreacter <V extends IBaseView> {
    private WeakReference<V> vWeakReference;
    public BasePreacter(V v) {
        vWeakReference = new WeakReference<>(v);
        iniModel();
    }
    protected   abstract void iniModel();
    public V getView(){
        if(vWeakReference!=null){
            return vWeakReference.get();
        }
        return null;
    }
    protected void datachView(){
        if(vWeakReference!=null){
            vWeakReference.clear();
        }
        vWeakReference=null;
    }


}
