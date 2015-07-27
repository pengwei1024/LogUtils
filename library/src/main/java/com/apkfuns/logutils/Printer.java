package com.apkfuns.logutils;

/**
 * Created by pengwei08 on 2015/7/20.
 */
public interface Printer {
    void d(StackTraceElement element, String message, Object... args);
    void d(StackTraceElement element, Object object);

    void e(StackTraceElement element, String message, Object... args);
    void e(StackTraceElement element, Object object);

    void w(StackTraceElement element, String message, Object... args);
    void w(StackTraceElement element, Object object);

    void i(StackTraceElement element, String message, Object... args);
    void i(StackTraceElement element, Object object);

    void v(StackTraceElement element, String message, Object... args);
    void v(StackTraceElement element, Object object);

    void wtf(StackTraceElement element, String message, Object... args);
    void wtf(StackTraceElement element, Object object);

    void json(StackTraceElement element, String json);
}
