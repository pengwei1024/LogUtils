package com.apkfuns.logutils;

import android.util.Log;

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
        String tag = "";
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
        } else {
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
    public void e(Throwable throwable, String message, Object... args) {

    }

    @Override
    public void e(String message, Object... args) {

    }

    @Override
    public void e(Object object) {

    }

    @Override
    public void w(String message, Object... args) {

    }

    @Override
    public void w(Object object) {

    }

    @Override
    public void i(String message, Object... args) {

    }

    @Override
    public void i(Object object) {

    }

    @Override
    public void v(String message, Object... args) {

    }

    @Override
    public void v(Object object) {

    }

    @Override
    public void wtf(String message, Object... args) {

    }

    @Override
    public void wtf(Object object) {

    }
}
