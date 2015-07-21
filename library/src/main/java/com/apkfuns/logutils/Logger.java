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
     * 打印日志
     *
     * @param type
     * @param msg
     * @param args
     */
    private void log(LogType type, String msg, Object... args) {
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


    private void log(LogType type, Object object) {
        if (object instanceof Throwable) {
            String tag = "";
            String msg = null;
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
        } else if(object instanceof String){
            log(type, (String) object, null);
        }else {
            log(type, SystemUtil.objectToString(object));
        }
    }

    @Override
    public void d(String message, Object... args) {
        log(LogType.Debug, message, args);
    }

    @Override
    public void d(Object object) {
        log(LogType.Debug, object);
    }

    @Override
    public void e(String message, Object... args) {
        log(LogType.Error, message, args);
    }

    @Override
    public void e(Object object) {
        log(LogType.Error, object);
    }

    @Override
    public void w(String message, Object... args) {
        log(LogType.Warn, message, args);
    }

    @Override
    public void w(Object object) {
        log(LogType.Warn, object);
    }

    @Override
    public void i(String message, Object... args) {
        log(LogType.Info, message, args);
    }

    @Override
    public void i(Object object) {
        log(LogType.Info, object);
    }

    @Override
    public void v(String message, Object... args) {
        log(LogType.Verbose, message, args);
    }

    @Override
    public void v(Object object) {
        log(LogType.Verbose, object);
    }

    @Override
    public void wtf(String message, Object... args) {
        log(LogType.Wtf, message, args);
    }

    @Override
    public void wtf(Object object) {
        log(LogType.Wtf, object);
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
        String Prefix = TextUtils.isEmpty(configTagPrefix) ? "" : configTagPrefix + "-";
        tag = String.format(tag, Prefix, callerClazzName, caller.getMethodName(), stackTrace);
        return tag;
    }
}
