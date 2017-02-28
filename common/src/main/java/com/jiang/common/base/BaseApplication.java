package com.jiang.common.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.support.multidex.MultiDex;

public class BaseApplication extends Application {


    private static final String TAG = "BaseApplication";
    private static BaseApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    public static Context getAppContext() {
        return instance;
    }

    public static Resources getAppResources() {
        return instance.getResources();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    public static BaseApplication getInstance() {
        return instance;
    }

    /**
     * 分包
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}



