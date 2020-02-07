package com.behavior.bottombehaviormaster;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * @author Lix
 * @date 2020-02-01 21:30
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //工具类初始化
        Utils.init(this);
    }
}
