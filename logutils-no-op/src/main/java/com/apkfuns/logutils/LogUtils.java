package com.apkfuns.logutils;

/**
 * Created by pengwei on 16/7/18.
 */
public final class LogUtils {

    /**
     * 选项配置
     *
     * @return
     */
    public static LogConfig getLogConfig() {
       return LogConfigImpl.getInstance();
    }

    public static Printer tag(String tag) {
        return Logger.getInstance();
    }

    /**
     * verbose输出
     *
     * @param msg
     * @param args
     */
    public static void v(String msg, Object... args) {

    }

    public static void v(Object object) {

    }


    /**
     * debug输出
     *
     * @param msg
     * @param args
     */
    public static void d(String msg, Object... args) {

    }

    public static void d(Object object) {

    }

    /**
     * info输出
     *
     * @param msg
     * @param args
     */
    public static void i(String msg, Object... args) {

    }

    public static void i(Object object) {

    }

    /**
     * warn输出
     *
     * @param msg
     * @param args
     */
    public static void w(String msg, Object... args) {

    }

    public static void w(Object object) {

    }

    /**
     * error输出
     *
     * @param msg
     * @param args
     */
    public static void e(String msg, Object... args) {

    }

    public static void e(Object object) {

    }

    /**
     * assert输出
     *
     * @param msg
     * @param args
     */
    public static void wtf(String msg, Object... args) {

    }

    public static void wtf(Object object) {

    }

    /**
     * 打印json
     *
     * @param json
     */
    public static void json(String json) {

    }

    /**
     * 输出xml
     * @param xml
     */
    public static void xml(String xml) {

    }
}
