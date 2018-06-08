package com.apkfuns.logutils;

import com.apkfuns.logutils.file.LogFileEngine;
import com.apkfuns.logutils.file.LogFileFilter;

import java.io.File;

/**
 * Created by pengwei on 2017/3/30.
 */

class Log2FileConfigImpl implements Log2FileConfig {

    private static Log2FileConfigImpl singleton;

    public static Log2FileConfigImpl getInstance() {
        if (singleton == null) {
            synchronized (Log2FileConfigImpl.class) {
                if (singleton == null) {
                    singleton = new Log2FileConfigImpl();
                }
            }
        }
        return singleton;
    }

    @Override
    public Log2FileConfig configLog2FileEnable(boolean enable) {
        return this;
    }

    @Override
    public Log2FileConfig configLog2FilePath(String logPath) {
        return this;
    }


    @Override
    public Log2FileConfig configLog2FileNameFormat(String formatName) {
        return this;
    }

    @Override
    public Log2FileConfig configLog2FileLevel(@LogLevel.LogLevelType int level) {
        return this;
    }

    @Override
    public Log2FileConfig configLogFileEngine(LogFileEngine engine) {
        return this;
    }

    @Override
    public Log2FileConfig configLogFileFilter(LogFileFilter fileFilter) {
        return this;
    }

    @Override
    public File getLogFile() {
        return null;
    }

    @Override
    public void flushAsync() {

    }

    @Override
    public void release() {

    }
}
