package com.apkfuns.logutils.file;

/**
 * Created by pengwei on 2017/3/30.
 */

public class LogFileParam {

    private long time;
    private int logLevel;
    private String threadName;
    private String tagName;

    public LogFileParam(long time, int logLevel, String threadName, String tagName) {
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
