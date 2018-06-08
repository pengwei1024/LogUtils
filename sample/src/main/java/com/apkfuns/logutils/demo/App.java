package com.apkfuns.logutils.demo;

import android.app.Application;
import android.os.Environment;

import com.apkfuns.log2file.LogFileEngineFactory;
import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.LogUtils;

/**
 * Created by pengwei on 16/3/22.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.getLogConfig()
                .configAllowLog(true)
                .configTagPrefix("LogUtilsDemo")
                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}")
                .configShowBorders(true)
//                .configMethodOffset(1)
//                .addParserClass(OkHttpResponseParse.class)
                .configLevel(LogLevel.TYPE_VERBOSE);

        // 支持输入日志到文件
        String filePath = Environment.getExternalStorageDirectory() + "/LogUtils/logs/";
        LogUtils.getLog2FileConfig().configLog2FileEnable(true)
                .configLog2FilePath(filePath)
                .configLog2FileNameFormat("app-%d{yyyyMMdd}.txt")
                .configLog2FileLevel(LogLevel.TYPE_VERBOSE)
                .configLogFileEngine(new LogFileEngineFactory(this));
    }
}
