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
        LogUtils.getLogConfig().configAllowLog(true).configTagPrefix("LogUtilsDemo")
                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}").configShowBorders(true)
                .configLevel(LogLevel.TYPE_VERBOSE).addParserClass(OkHttpResponseParse.class);


        // configLog2FileEnable(boolean enable)
        // configLog2FilePath(String path)
        // configLog2FileFormatName(String format)
        // configLog2FileLevel(int level)
        // configLog2FileCycle(月、日、小时)
    }
}
