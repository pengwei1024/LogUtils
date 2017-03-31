package com.apkfuns.logutils.demo;

import android.app.Application;

import com.apkfuns.log2file.LogFileEngineFactory;
import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.LogUtils;
import com.apkfuns.logutils.demo.parse.OkHttpResponseParse;
import com.apkfuns.logutils.file.LogFileFilter;

/**
 * Created by pengwei on 16/3/22.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.getLogConfig().configAllowLog(true).configTagPrefix("LogUtilsDemo")
                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}").configShowBorders(true)
//                .configMethodOffset(1)
                .configLevel(LogLevel.TYPE_VERBOSE).addParserClass(OkHttpResponseParse.class);
        LogUtils.getLog2FileConfig().configLog2FileEnable(true)
                .configLog2FilePath("/sdcard/LogUtils/logs/")
                .configLog2FileNameFormat("Hi-%d{yyyyMMdd}-1.txt")
                .configLog2FileLevel(LogLevel.TYPE_VERBOSE)
//                .configLogFileFilter(new LogFileFilter() {
//                        @Override
//                        public boolean accept(@LogLevel.LogLevelType int level, String tag, String logContent) {
//                            if (logContent.contains("name")) {
//                                return false;
//                            }
//                            return true;
//                        }
//                })
                .configLogFileEngine(new LogFileEngineFactory());
    }
}
