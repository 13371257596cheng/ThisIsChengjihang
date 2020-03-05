package com.bawei.thisischengjihang.base;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
//        CrashReport.initCrashReport(getApplicationContext(), "59e191bf27", true);
    }

    public static Context getAppction(){
        return context;
    }

}
