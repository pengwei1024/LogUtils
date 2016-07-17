package com.apkfuns.logutils.demo;

import android.app.Application;

import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.LogUtils;
import com.apkfuns.logutils.demo.parse.OkHttpResponseParse;

/**
 * Created by pengwei on 16/3/22.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.getLogConfig()
                .configAllowLog(false)
                .configTagPrefix("LogUtilsDemo")
                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}")
                .configShowBorders(true)
                .configLevel(LogLevel.TYPE_VERBOSE)
                .addParserClass(OkHttpResponseParse.class);
    }
}
