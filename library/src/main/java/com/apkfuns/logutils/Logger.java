package com.apkfuns.logutils;

import android.text.TextUtils;
import android.util.Log;

import static com.apkfuns.logutils.LogLevel.*;
import static com.apkfuns.logutils.utils.ObjectUtil.*;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.MissingFormatArgumentException;

/**
 * Created by pengwei08 on 2015/7/20.
 */
// TODO: 16/3/22 泛型支持
final class Logger implements Printer {

    private LogConfigImpl mLogConfig;

    protected Logger() {
        mLogConfig = LogConfigImpl.getInstance();
        mLogConfig.addParserClass(Constant.DEFAULT_PARSE_CLASS);
    }

    /**
     * 打印字符串
     *
     * @param type
     * @param msg
     * @param args
     */
    private void logString(@LogLevelType int type, String msg, Object... args) {
        logString(type, msg, false, args);
    }

    private void logString(@LogLevelType int type, String msg, boolean isPart, Object... args) {
        if (!mLogConfig.isEnable() || !LogUtils.configAllowLog) {
            return;
        }
        if (type < mLogConfig.getLogLevel()) {
            return;
        }
        String tag = generateTag();
        if (msg.length() > Constant.LINE_MAX) {
            if (mLogConfig.isShowBorder()) {
                printLog(type, tag, printDividingLine(DIVIDER_TOP));
                printLog(type, tag, printDividingLine(DIVIDER_NORMAL) + getTopStackInfo());
                printLog(type, tag, printDividingLine(DIVIDER_CENTER));
            }
            for (String subMsg : largeStringToList(msg)) {
                logString(type, subMsg, true, args);
            }
            if (mLogConfig.isShowBorder()) {
                printLog(type, tag, printDividingLine(DIVIDER_BOTTOM));
            }
            return;
        }
        if (args.length > 0) {
            try {
                msg = String.format(msg, args);
            } catch (MissingFormatArgumentException e) {

            }
        }
        if (mLogConfig.isShowBorder()) {
            if (isPart) {
                for (String sub : msg.split(Constant.LINE_SEPARATOR)) {
                    printLog(type, tag, printDividingLine(DIVIDER_NORMAL) + sub);
                }
            } else {
                printLog(type, tag, printDividingLine(DIVIDER_TOP));
                printLog(type, tag, printDividingLine(DIVIDER_NORMAL) + getTopStackInfo());
                printLog(type, tag, printDividingLine(DIVIDER_CENTER));
                for (String sub : msg.split(Constant.LINE_SEPARATOR)) {
                    printLog(type, tag, printDividingLine(DIVIDER_NORMAL) + sub);
                }
                printLog(type, tag, printDividingLine(DIVIDER_BOTTOM));
            }
        } else {
            printLog(type, tag, msg);
        }
    }


    /**
     * 打印对象
     *
     * @param type
     * @param object
     */
    private void logObject(@LogLevelType int type, Object object) {
        if (object != null) {
            for (Parser parser : mLogConfig.getParseList()) {
                if (parser.parseClassType().isAssignableFrom(object.getClass())) {
                    logString(type, parser.parseString(object));
                    return;
                }
            }
        }
        logString(type, objectToString(object));
    }

    /**
     * 自动生成tag
     *
     * @return
     */
    private String generateTag() {
        if (!mLogConfig.isShowBorder()) {
            return mLogConfig.getTagPrefix() + getTopStackInfo();
        }
        return mLogConfig.getTagPrefix();
    }

    /**
     * 获取最顶部stack信息
     *
     * @return
     */
    private String getTopStackInfo() {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        StackTraceElement caller = trace[8];
        String stackTrace = caller.toString();
        stackTrace = stackTrace.substring(stackTrace.lastIndexOf('('), stackTrace.length());
        String tag = "%s.%s%s";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), stackTrace);
        return tag;
    }

    @Override
    public void d(String message, Object... args) {
        logString(TYPE_DEBUG, message, args);
    }

    @Override
    public void d(Object object) {
        logObject(TYPE_DEBUG, object);
    }

    @Override
    public void e(String message, Object... args) {
        logString(TYPE_ERROR, message, args);
    }

    @Override
    public void e(Object object) {
        logObject(TYPE_ERROR, object);
    }

    @Override
    public void w(String message, Object... args) {
        logString(TYPE_WARM, message, args);
    }

    @Override
    public void w(Object object) {
        logObject(TYPE_WARM, object);
    }

    @Override
    public void i(String message, Object... args) {
        logString(TYPE_INFO, message, args);
    }

    @Override
    public void i(Object object) {
        logObject(TYPE_INFO, object);
    }

    @Override
    public void v(String message, Object... args) {
        logString(TYPE_VERBOSE, message, args);
    }

    @Override
    public void v(Object object) {
        logObject(TYPE_VERBOSE, object);
    }

    @Override
    public void wtf(String message, Object... args) {
        logString(TYPE_WTF, message, args);
    }

    @Override
    public void wtf(Object object) {
        logObject(TYPE_WTF, object);
    }

    @Override
    public void json(String json) {
        int indent = 4;
        if (TextUtils.isEmpty(json)) {
            d("JSON{json is null}");
            return;
        }
        try {
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                String msg = jsonObject.toString(indent);
                d(msg);
            } else if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                String msg = jsonArray.toString(indent);
                d(msg);
            }
        } catch (JSONException e) {
            e(e);
        }
    }


    /**
     * 打印日志
     *
     * @param type
     * @param tag
     * @param msg
     */
    private void printLog(@LogLevelType int type, String tag, String msg) {
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

}
