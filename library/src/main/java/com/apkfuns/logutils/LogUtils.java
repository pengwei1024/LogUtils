package com.apkfuns.logutils;

import android.text.TextUtils;
import android.util.Log;

import com.apkfuns.logutils.utils.SystemUtil;

import java.lang.reflect.Field;

/**
 * Created by pengwei08 on 2015/7/16.
 * 日志管理器
 */
public abstract class LogUtils {

    // 基本数据类型
    private final static String[] types = {"int", "java.lang.String", "boolean", "char",
            "float", "double", "long", "short", "byte"};

    // 日志类型
    private enum LogType {
        Verbose, Debug, Info, Warn, Error
    }

    // 允许输出日志
    public static boolean configAllowLog = true;
    // 配置日志Tag前缀
    public static String configTagPrefix = "";

    /**
     * 自动生成tag
     *
     * @return
     */
    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = TextUtils.isEmpty(configTagPrefix) ? tag : configTagPrefix + ":" + tag;
        tag = caller.toString();
        tag = tag.substring(tag.lastIndexOf('('), tag.length());
        return tag;
    }


    /**
     * 日志输出
     *
     * @param tag
     * @param content
     */
    private static void log(LogType type, String tag, String content) {
        if (!configAllowLog) return;
        switch (type) {
            case Verbose:
                Log.v(tag, content);
                break;
            case Debug:
                Log.d(tag, content);
                break;
            case Info:
                Log.i(tag, content);
                break;
            case Warn:
                Log.w(tag, content);
                break;
            case Error:
                Log.e(tag, content);
                break;
            default:
                break;
        }

    }
    private static void log(LogType type, String content) {
        log(type, generateTag(SystemUtil.getStackTrace()), content);
    }
    private static void log(LogType type, Object object){
        log(type, toString(object));
    }
    private static void log(LogType type, String content, Object... objects){
        log(type, String.format(content, objects));
    }

    /**
     * debug输出
     *
     * @param tag
     * @param content
     */
    public static void d(String tag, String content) {
        log(LogType.Debug, tag, content);
    }

    public static void d(String content) {
        log(LogType.Debug, content);
    }

    public static void d(Object object) {
        log(LogType.Debug, object);
    }

    public static void d(String content, Object... objects) {
        log(LogType.Debug, content, objects);
    }

    public static void e(String tag, String content) {
        log(LogType.Error, tag, content);
    }
    public static void e(String content) {
        log(LogType.Error, content);
    }
    public static void e(Object object) {
        log(LogType.Error, object);
    }
    public static void e(String content, Object... objects) {
        log(LogType.Error, content, objects);
    }

    public static void i(String tag, String content) {
        log(LogType.Info, tag, content);
    }
    public static void i(String content) {
        log(LogType.Info, content);
    }
    public static void i(Object object) {
        log(LogType.Info, object);
    }
    public static void i(String content, Object... objects) {
        log(LogType.Info, content, objects);
    }

    public static void v(String tag, String content) {
        log(LogType.Verbose, tag, content);
    }
    public static void v(String content) {
        log(LogType.Verbose, content);
    }
    public static void v(Object object) {
        log(LogType.Verbose, object);
    }
    public static void v(String content, Object...objects) {
        log(LogType.Verbose, content, objects);
    }

    public static void w(String tag, String content) {
        log(LogType.Warn, tag, content);
    }
    public static void w(String content) {
        log(LogType.Warn, content);
    }
    public static void w(Object object) {
        log(LogType.Warn, object);
    }
    public static void w(String content, Object...objects) {
        log(LogType.Warn, content, objects);
    }

    /**
     * 将对象转化为String
     *
     * @param object
     * @return
     */
    private static <T> String toString(T object) {
        if (object == null) {
            return "Null{object is null}";
        }
        if (object.toString().startsWith(object.getClass().getName() + "@")) {
            StringBuilder builder = new StringBuilder(object.getClass().getSimpleName() + "{");
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                for (String type : types) {
                    if (field.getType().getName().equalsIgnoreCase(type)) {
                        try {
                            Object value = field.get(object);
                            builder.append(String.format("%s=%s, ", field.getName(),
                                    value == null ? "null" : value.toString()));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
            return builder.replace(builder.length() - 2, builder.length() - 1, "}").toString();
        } else {
            return object.toString();
        }
    }
}
