package com.apkfuns.logutils;

/**
 * Created by pengwei on 16/7/18.
 */
class LogConfigImpl implements LogConfig {

    private volatile static LogConfigImpl singleton;
    
    private LogConfigImpl() {
    }

    static LogConfigImpl getInstance() {
        if (singleton == null) {
            synchronized (LogConfigImpl.class) {
                if (singleton == null) {
                    singleton = new LogConfigImpl();
                }
            }
        }
        return singleton;
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
    public LogConfig configFormatTag(String formatTag) {
        return this;
    }

    @Override
    public LogConfig configShowBorders(boolean showBorder) {
        return this;
    }

    @Override
    public LogConfig configLevel(@LogLevel.LogLevelType int logLevel) {
        return this;
    }

    @SafeVarargs
    @Override
    public final LogConfig addParserClass(Class<? extends Parser>... classes) {
        return this;
    }

    @Override
    public LogConfig configMethodOffset(int offset) {
        return this;
    }
}
