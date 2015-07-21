package com.apkfuns.logutils;

import android.text.TextUtils;
import android.util.Log;

import static com.apkfuns.logutils.LogUtils.*;

import com.apkfuns.logutils.utils.SystemUtil;

/**
 * Created by pengwei08 on 2015/7/20.
 */
public final class Logger implements LogImpl {

    /**
     * 打印字符串
     *
     * @param type
     * @param msg
     * @param args
     */
    private void logString(LogType type, String msg, Object... args) {
        if (!LogUtils.configAllowLog) {
            return;
        }
        String tag = generateTag(SystemUtil.getStackTrace());
        msg = String.format(msg, args);
        switch (type) {
            case Verbose:
                Log.v(tag, msg);
                break;
            case Debug:
                Log.d(tag, msg);
                break;
            case Info:
                Log.i(tag, msg);
                break;
            case Warn:
                Log.w(tag, msg);
                break;
            case Error:
                Log.e(tag, msg);
                break;
            case Wtf:
                Log.wtf(tag, msg);
                break;
            default:
                break;
        }
    }

    /**
     * 打印对象
     * @param type
     * @param object
     */
    private void logObject(LogType type, Object object) {
        if(object != null){
            if (object instanceof Throwable) {
                String tag = generateTag(SystemUtil.getStackTrace());
                String msg = object.toString();
                Exception exception = (Exception) object;
                switch (type) {
                    case Verbose:
                        Log.v(tag, msg, exception);
                        break;
                    case Debug:
                        Log.d(tag, msg, exception);
                        break;
                    case Info:
                        Log.i(tag, msg, exception);
                        break;
                    case Warn:
                        Log.w(tag, msg, exception);
                        break;
                    case Error:
                        Log.e(tag, msg, exception);
                        break;
                    case Wtf:
                        Log.wtf(tag, msg, exception);
                        break;
                    default:
                        break;
                }
            } else if (object instanceof String) {
                logString(type, (String) object);
            } else {
                logString(type, SystemUtil.objectToString(object));
            }
        }else{
            logString(type, "Null{object is null}");
        }
    }

    @Override
    public void d(String message, Object... args) {
        logString(LogType.Debug, message, args);
    }

    @Override
    public void d(Object object) {
        logObject(LogType.Debug, object);
    }

    @Override
    public void e(String message, Object... args) {
        logString(LogType.Error, message, args);
    }

    @Override
    public void e(Object object) {
        logObject(LogType.Error, object);
    }

    @Override
    public void w(String message, Object... args) {
        logString(LogType.Warn, message, args);
    }

    @Override
    public void w(Object object) {
        logObject(LogType.Warn, object);
    }

    @Override
    public void i(String message, Object... args) {
        logString(LogType.Info, message, args);
    }

    @Override
    public void i(Object object) {
        logObject(LogType.Info, object);
    }

    @Override
    public void v(String message, Object... args) {
        logString(LogType.Verbose, message, args);
    }

    @Override
    public void v(Object object) {
        logObject(LogType.Verbose, object);
    }

    @Override
    public void wtf(String message, Object... args) {
        logString(LogType.Wtf, message, args);
    }

    @Override
    public void wtf(Object object) {
        logObject(LogType.Wtf, object);
    }


    /**
     * 自动生成tag
     *
     * @return
     */
    private String generateTag(StackTraceElement caller) {
        String stackTrace = caller.toString();
        stackTrace = stackTrace.substring(stackTrace.lastIndexOf('('), stackTrace.length());
        String tag = "%s%s.%s%s";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        String Prefix = TextUtils.isEmpty(configTagPrefix) ? "" : configTagPrefix;
        tag = String.format(tag, Prefix, callerClazzName, caller.getMethodName(), stackTrace);
        return tag;
    }
}
