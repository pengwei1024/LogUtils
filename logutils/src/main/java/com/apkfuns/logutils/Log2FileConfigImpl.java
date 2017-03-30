package com.apkfuns.logutils;

import com.apkfuns.logutils.file.LogFileEngine;

/**
 * Created by pengwei on 2017/3/30.
 */

class Log2FileConfigImpl implements Log2FileConfig {

    private LogFileEngine engine;
    private boolean enable = false;
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
        this.enable = enable;
        return this;
    }

    public boolean isEnable() {
        return enable;
    }

    @Override
    public Log2FileConfig configLog2FilePath(String logPath) {
        return null;
    }

    @Override
    public Log2FileConfig configLog2FileLevel(@LogLevel.LogLevelType int level) {
        return null;
    }

    @Override
    public Log2FileConfig setLogFileEngine(LogFileEngine engine) {
        this.engine = engine;
        return this;
    }

    public LogFileEngine getEngine() {
        return engine;
    }
}
