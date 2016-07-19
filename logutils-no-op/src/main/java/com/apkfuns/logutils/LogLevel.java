package com.apkfuns.logutils;

/**
 * Created by pengwei on 16/3/3.
 */
public class LogLevel {
    public static final int TYPE_VERBOSE = 1;
    public static final int TYPE_DEBUG = 2;
    public static final int TYPE_INFO = 3;
    public static final int TYPE_WARM = 4;
    public static final int TYPE_ERROR = 5;
    public static final int TYPE_WTF = 6;

    public @interface LogLevelType {
    }
}
