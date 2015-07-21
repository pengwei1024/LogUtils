package com.apkfuns.logutils;

import android.text.TextUtils;
import android.util.Log;

import com.apkfuns.logutils.utils.SystemUtil;

import java.lang.reflect.Field;

/**
 * Created by pengwei08 on 2015/7/16.
 * 日志管理器
 */
public abstract class LogUtils  {

    private static Logger logger;
    static {
        logger = new Logger();
    }

    // 允许输出日志
    public static boolean configAllowLog = true;

    // 配置日志Tag前缀
    public static String configTagPrefix = "";

    /**
     * verbose输出
     * @param msg
     * @param args
     */
    public static void v(String msg, Object... args){
        logger.v(msg, args);
    }
    public static void v(Object object){
        logger.v(object);
    }


    /**
     * debug输出
     * @param msg
     * @param args
     */
    public static void d(String msg, Object... args) {
        logger.d(msg, args);
    }

    public static void d(Object object) {
        logger.d(object);
    }

    /**
     * info输出
     * @param msg
     * @param args
     */
    public static void i(String msg, Object... args){
        logger.i(msg, args);
    }
    public static void i(Object object){
        logger.i(object);
    }

    /**
     * warn输出
     * @param msg
     * @param args
     */
    public static void w(String msg, Object... args){
        logger.w(msg, args);
    }
    public static void w(Object object){
        logger.w(object);
    }

    /**
     * error输出
     * @param msg
     * @param args
     */
    public static void e(String msg, Object... args){
        logger.e(msg, args);
    }
    public static void e(Object object){
        logger.e(object);
    }

    /**
     * assert输出
     * @param msg
     * @param args
     */
    public static void wtf(String msg, Object... args){
        logger.wtf(msg, args);
    }
    public static void wtf(Object object){
        logger.wtf(object);
    }

}
