package com.apkfuns.logutils;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

import static com.apkfuns.logutils.LogUtils.*;
import static com.apkfuns.logutils.LogLevel.*;

import com.apkfuns.logutils.utils.ArrayUtil;
import com.apkfuns.logutils.utils.CommonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.MissingFormatArgumentException;
import java.util.Set;

/**
 * Created by pengwei08 on 2015/7/20.
 */
final class Logger implements Printer {

    private LogConfig logConfig;

    protected Logger() {
        logConfig = LogConfigImpl.getInstance();
    }

    public LogConfig getLogConfig() {
        return logConfig;
    }

    /**
     * 打印字符串
     *
     * @param type
     * @param element
     * @param msg
     * @param args
     */
    private void logString(@LogLevelType int type, StackTraceElement element, String msg, Object... args) {
        if (!LogUtils.configAllowLog) {
            return;
        }
        String tag = generateTag(element);
        if (args.length > 0) {
            try {
                msg = String.format(msg, args);
            } catch (MissingFormatArgumentException e) {

            }
        }
        switch (type) {
            case TYPE_VERBOSE:
                Log.v(tag, msg);
                break;
            case TYPE_DEBUG:
                Log.d(tag, msg);
                break;
            case TYPE_INFO:
                Log.i(tag, msg);
                break;
            case TYPE_WARM:
                Log.w(tag, msg);
                break;
            case TYPE_ERROR:
                Log.e(tag, msg);
                break;
            case TYPE_WTF:
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
    private void logObject(@LogLevelType int type, StackTraceElement element, Object object) {
        if (!LogUtils.configAllowLog) {
            return;
        }
        if (object != null) {
            final String simpleName = object.getClass().getSimpleName();
            if (object instanceof Throwable) {
                String tag = generateTag(element);
                String msg = object.toString();
                Throwable exception = (Throwable) object;
                switch (type) {
                    case TYPE_VERBOSE:
                        Log.v(tag, msg, exception);
                        break;
                    case TYPE_DEBUG:
                        Log.d(tag, msg, exception);
                        break;
                    case TYPE_INFO:
                        Log.i(tag, msg, exception);
                        break;
                    case TYPE_WARM:
                        Log.w(tag, msg, exception);
                        break;
                    case TYPE_ERROR:
                        Log.e(tag, msg, exception);
                        break;
                    case TYPE_WTF:
                        Log.wtf(tag, msg, exception);
                        break;
                    default:
                        break;
                }
            } else if (object instanceof String) {
                logString(type, element, (String) object);
            } else if (object.getClass().isArray()) {
                // TODO: 16/3/4 支持二维数组+
                String msg = "Temporarily not support more than two dimensional Array!";
                int dim = ArrayUtil.getArrayDimension(object);
                switch (dim) {
                    case 1:
                        Pair pair = ArrayUtil.arrayToString(object);
                        msg = simpleName.replace("[]", "[" + pair.first + "] {\n");
                        msg += pair.second + "\n";
                        break;
                    case 2:
                        Pair pair1 = ArrayUtil.arrayToObject(object);
                        Pair pair2 = (Pair) pair1.first;
                        msg = simpleName.replace("[][]", "[" + pair2.first + "][" + pair2.second + "] {\n");
                        msg += pair1.second + "\n";
                        break;
                    default:
                        break;
                }
                logString(type, element, msg + "}");
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
                        msg += String.format(itemString, flag, CommonUtil.objectToString(item),
                                flag++ < collection.size() - 1 ? ",\n" : "\n");
                    }
                }
                logString(type, element, msg + "\n]");
            } else if (object instanceof Map) {
                String msg = simpleName + " {\n";
                Map<Object, Object> map = (Map<Object, Object>) object;
                Set<Object> keys = map.keySet();
                for (Object key : keys) {
                    String itemString = "[%s -> %s]\n";
                    Object value = map.get(key);
                    msg += String.format(itemString, CommonUtil.objectToString(key),
                            CommonUtil.objectToString(value));
                }
                logString(type, element, msg + "}");
            } else if (object instanceof Intent) {
                logString(type, element, printIntent((Intent) object));
            } else if (object instanceof Bundle) {
                logString(type, element, printBundle((Bundle) object));
            } else {
                logString(type, element, CommonUtil.objectToString(object));
            }
        } else {
            logString(type, element, CommonUtil.objectToString(object));
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
        logString(TYPE_DEBUG, element, message, args);
    }

    @Override
    public void d(StackTraceElement element, Object object) {
        logObject(TYPE_DEBUG, element, object);
    }

    @Override
    public void e(StackTraceElement element, String message, Object... args) {
        logString(TYPE_ERROR, element, message, args);
    }

    @Override
    public void e(StackTraceElement element, Object object) {
        logObject(TYPE_ERROR, element, object);
    }

    @Override
    public void w(StackTraceElement element, String message, Object... args) {
        logString(TYPE_WARM, element, message, args);
    }

    @Override
    public void w(StackTraceElement element, Object object) {
        logObject(TYPE_WARM, element, object);
    }

    @Override
    public void i(StackTraceElement element, String message, Object... args) {
        logString(TYPE_INFO, element, message, args);
    }

    @Override
    public void i(StackTraceElement element, Object object) {
        logObject(TYPE_INFO, element, object);
    }

    @Override
    public void v(StackTraceElement element, String message, Object... args) {
        logString(TYPE_VERBOSE, element, message, args);
    }

    @Override
    public void v(StackTraceElement element, Object object) {
        logObject(TYPE_VERBOSE, element, object);
    }

    @Override
    public void wtf(StackTraceElement element, String message, Object... args) {
        logString(TYPE_WTF, element, message, args);
    }

    @Override
    public void wtf(StackTraceElement element, Object object) {
        logObject(TYPE_WTF, element, object);
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

    /**
     * 打印Intent的信息
     *
     * @param it
     */
    private String printIntent(Intent it) {
        return it.toString();
    }

    /**
     * 打印bundle
     *
     * @param bundle
     */
    private String printBundle(Bundle bundle) {
        StringBuilder builder = new StringBuilder("Bundle[\n");
        for (String key : bundle.keySet()) {
            builder.append("'" + key + "' => " + CommonUtil.objectToString(bundle.get(key)) + "\n");
        }
        builder.append("]\n");
        return builder.toString();
    }
}
