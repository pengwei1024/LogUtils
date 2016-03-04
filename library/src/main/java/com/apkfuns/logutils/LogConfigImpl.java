package com.apkfuns.logutils;


/**
 * Created by pengwei on 16/3/4.
 * Log config
 */
class LogConfigImpl implements LogConfig {

    private static LogConfig singleton;

    private LogConfigImpl() {
    }

    static LogConfig getInstance() {
        return singleton == null ? singleton = new LogConfigImpl() : singleton;
    }

    @Override
    public LogConfig configAllowLog(boolean allowLog) {
        return this;
    }

    @Override
    public LogConfig configTagPrefix(String prefix) {
        return this;
    }

    @Override
    public LogConfig configShowBorders(boolean showBorder) {
        return this;
    }

    @Override
    public LogConfig configLevel(@LogLevel.LogLevelType int logType) {
        return this;
    }
}
