package com.zeng.toolbar.app;

import android.app.Application;

import org.litepal.LitePal;

/**
 * Created by Administrator on 2017/1/30 0030.
 */
public class MyApplication extends Application
{
    private static MyApplication mInstance;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mInstance = this;
        // 调用 LitePal 的初始化方法
        LitePal.initialize(this);

    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
}
