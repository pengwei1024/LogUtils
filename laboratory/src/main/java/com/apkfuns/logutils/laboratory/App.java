package com.apkfuns.logutils.laboratory;

import android.app.Application;

import com.apkfuns.logutils.LogUtils;

/**
 * Created by pengwei on 16/6/3.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        LogUtils.getLogConfig().configShowBorders(true);
    }
}
