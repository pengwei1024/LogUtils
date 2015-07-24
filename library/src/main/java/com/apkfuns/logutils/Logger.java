package com.apkfuns.logutils;

import android.text.TextUtils;
import android.util.Log;

import static com.apkfuns.logutils.LogUtils.*;

import com.apkfuns.logutils.utils.SystemUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by pengwei08 on 2015/7/20.
 */
public final class Logger implements LogImpl {

    /**
     * 打印字符串
     *
     * @param type
     * @param element
     * @param msg
     * @param args
     */
    private void logString(LogType type, StackTraceElement element, String msg, Object... args) {
        if (!LogUtils.configAllowLog) {
            return;
        }
        String tag = generateTag(element);
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
     *
     * @param type
     * @param element
     * @param object
     */
    private void logObject(LogType type, StackTraceElement element, Object object) {
        if (object != null) {
            final String simpleName = object.getClass().getSimpleName();
            if (object instanceof Throwable) {
                String tag = generateTag(element);
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
                logString(type, element, (String) object);
            } else if (object instanceof Collection) {
                Collection collection = (Collection) object;
                String msg = "%s size = %d [\n";
                msg = String.format(msg, simpleName, collection.size());
                if (!collection.isEmpty()) {
                    Iterator<Object> iterator = collection.iterator();
                    int flag = 0;
                    while (iterator.hasNext()) {
                        String itemString = "[%d]:%s%s";
                        Object item = iterator.next();
                        msg += String.format(itemString, flag, SystemUtil.objectToString(item),
                                flag++ < collection.size() - 1 ? ",\n" : "\n");
                    }
                }
                logString(type, element, msg + "\n]");
            } else if (object instanceof Map) {
                String msg = simpleName + " {\n";
                Map<Object, Object> map = (Map<Object, Object>) object;
                Set<Object> keys = map.keySet();
                for (Object key : keys){
                    String itemString = "[%s -> %s]\n";
                    Object value = map.get(key);
                    msg += String.format(itemString, SystemUtil.objectToString(key),
                            SystemUtil.objectToString(value));
                }
                logString(type, element, msg + "}");
            } else {
                logString(type, element, SystemUtil.objectToString(object));
            }
        } else {
            logString(type, element, "Null{object is null}");
        }
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

    @Override
    public void d(StackTraceElement element, String message, Object... args) {
        logString(LogType.Debug, element, message, args);
    }

    @Override
    public void d(StackTraceElement element, Object object) {
        logObject(LogType.Debug, element, object);
    }

    @Override
    public void e(StackTraceElement element, String message, Object... args) {
        logString(LogType.Error, element, message, args);
    }

    @Override
    public void e(StackTraceElement element, Object object) {
        logObject(LogType.Error, element, object);
    }

    @Override
    public void w(StackTraceElement element, String message, Object... args) {
        logString(LogType.Warn, element, message, args);
    }

    @Override
    public void w(StackTraceElement element, Object object) {
        logObject(LogType.Warn, element, object);
    }

    @Override
    public void i(StackTraceElement element, String message, Object... args) {
        logString(LogType.Info, element, message, args);
    }

    @Override
    public void i(StackTraceElement element, Object object) {
        logObject(LogType.Info, element, object);
    }

    @Override
    public void v(StackTraceElement element, String message, Object... args) {
        logString(LogType.Verbose, element, message, args);
    }

    @Override
    public void v(StackTraceElement element, Object object) {
        logObject(LogType.Verbose, element, object);
    }

    @Override
    public void wtf(StackTraceElement element, String message, Object... args) {
        logString(LogType.Wtf, element, message, args);
    }

    @Override
    public void wtf(StackTraceElement element, Object object) {
        logObject(LogType.Wtf, element, object);
    }

    @Override
    public void json(StackTraceElement element, String json) {
        int indent = 4;
        if (TextUtils.isEmpty(json)) {
            d(element, "JSON{json is null}");
            return;
        }
        try {
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                String msg = jsonObject.toString(indent);
                d(element, msg);
            } else if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                String msg = jsonArray.toString(indent);
                d(element, msg);
            }
        } catch (JSONException e) {
            e(element, e);
        }
    }
}
