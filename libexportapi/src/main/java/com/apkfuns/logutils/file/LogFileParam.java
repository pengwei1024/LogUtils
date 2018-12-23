package com.apkfuns.logutils.file;

import com.apkfuns.logutils.LogLevel;

/**
 * Created by pengwei on 2017/3/30.
 */

public class LogFileParam {

    private final long time;
    @LogLevel.LogLevelType
    private final int logLevel;
    private final String threadName;
    private final String tagName;

    public LogFileParam(long time, @LogLevel.LogLevelType int logLevel,
                        String threadName, String tagName) {
        this.time = time;
        this.logLevel = logLevel;
        this.threadName = threadName;
        this.tagName = tagName;
    }

    public long getTime() {
        return time;
    }

    public int getLogLevel() {
        return logLevel;
    }

    public String getThreadName() {
        return threadName;
    }

    public String getTagName() {
        return tagName;
    }
}
