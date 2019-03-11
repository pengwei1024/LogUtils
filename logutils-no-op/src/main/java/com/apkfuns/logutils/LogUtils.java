package com.apkfuns.logutils;

/**
 * Created by pengwei on 16/7/18.
 */
public final class LogUtils {

    public static LogConfig getLogConfig() {
        return LogConfigImpl.getInstance();
    }

    public static Log2FileConfig getLog2FileConfig() {
        return Log2FileConfigImpl.getInstance();
    }

    public static Printer tag(String tag) {
        return Logger.getInstance();
    }


    public static void v(String msg, Object... args) {
    }

    public static void v(Object object) {
    }


    public static void d(String msg, Object... args) {
    }

    public static void d(Object object) {
    }

    public static void i(String msg, Object... args) {
    }

    public static void i(Object object) {
    }

    public static void w(String msg, Object... args) {
    }

    public static void w(Object object) {
    }

    public static void e(String msg, Object... args) {
    }

    public static void e(Object object) {
    }

    public static void wtf(String msg, Object... args) {
    }

    public static void wtf(Object object) {
    }

    public static void json(String json) {
    }

    public static void xml(String xml) {
    }
}
