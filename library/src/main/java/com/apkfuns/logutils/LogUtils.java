package com.apkfuns.logutils;


import com.apkfuns.logutils.utils.CommonUtil;

/**
 * Created by pengwei08 on 2015/7/16.
 * 日志管理器
 */
public final class LogUtils {

    private static Printer printer;

    static {
        printer = new Logger();
    }

    /**
     * suggest use getLogConfig().configAllowLog() to config
     */
    @Deprecated
    public static boolean configAllowLog = true;

    /**
     * suggest use getLogConfig().configTagPrefix() to config
     */
    @Deprecated
    public static String configTagPrefix = "";

    /**
     * 选项配置
     *
     * @return
     */
    public static LogConfig getLogConfig() {
        return printer.getLogConfig();
    }

    /**
     * verbose输出
     *
     * @param msg
     * @param args
     */
    public static void v(String msg, Object... args) {
        printer.v(CommonUtil.getStackTrace(), msg, args);
    }

    public static void v(Object object) {
        printer.v(CommonUtil.getStackTrace(), object);
    }


    /**
     * debug输出
     *
     * @param msg
     * @param args
     */
    public static void d(String msg, Object... args) {
        printer.d(CommonUtil.getStackTrace(), msg, args);
    }

    public static void d(Object object) {
        printer.d(CommonUtil.getStackTrace(), object);
    }

    /**
     * info输出
     *
     * @param msg
     * @param args
     */
    public static void i(String msg, Object... args) {
        printer.i(CommonUtil.getStackTrace(), msg, args);
    }

    public static void i(Object object) {
        printer.i(CommonUtil.getStackTrace(), object);
    }

    /**
     * warn输出
     *
     * @param msg
     * @param args
     */
    public static void w(String msg, Object... args) {
        printer.w(CommonUtil.getStackTrace(), msg, args);
    }

    public static void w(Object object) {
        printer.w(CommonUtil.getStackTrace(), object);
    }

    /**
     * error输出
     *
     * @param msg
     * @param args
     */
    public static void e(String msg, Object... args) {
        printer.e(CommonUtil.getStackTrace(), msg, args);
    }

    public static void e(Object object) {
        printer.e(CommonUtil.getStackTrace(), object);
    }

    /**
     * assert输出
     *
     * @param msg
     * @param args
     */
    public static void wtf(String msg, Object... args) {
        printer.wtf(CommonUtil.getStackTrace(), msg, args);
    }

    public static void wtf(Object object) {
        printer.wtf(CommonUtil.getStackTrace(), object);
    }

    /**
     * 打印json
     *
     * @param json
     */
    public static void json(String json) {
        printer.json(CommonUtil.getStackTrace(), json);
    }

}
