package com.apkfuns.logutils;

import android.text.TextUtils;
import android.util.Log;

import static com.apkfuns.logutils.LogLevel.*;

import com.apkfuns.logutils.parser.BundleParse;
import com.apkfuns.logutils.parser.CollectionParse;
import com.apkfuns.logutils.parser.IntentParse;
import com.apkfuns.logutils.parser.MapParse;
import com.apkfuns.logutils.parser.ThrowableParse;
import com.apkfuns.logutils.utils.CommonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.MissingFormatArgumentException;

/**
 * Created by pengwei08 on 2015/7/20.
 */
final class Logger implements Printer {

    // 分割线方位
    public static final int DIR_TOP = 1;
    public static final int DIR_BOTTOM = 2;
    public static final int DIR_CENTER = 4;
    public static final int DIR_NORMAL = 3;

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    // 默认支持解析库
    public static final Class<? extends Parser>[] DEFAULT_PARSE_CLASS = new Class[]{
            BundleParse.class, IntentParse.class, CollectionParse.class,
            MapParse.class, ThrowableParse.class
    };

    private LogConfigImpl mLogConfig;

    protected Logger() {
        mLogConfig = LogConfigImpl.getInstance();
        mLogConfig.addParserClass(DEFAULT_PARSE_CLASS);
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
        logString(type, element, msg, false, args);
    }

    private void logString(@LogLevelType int type, StackTraceElement element, String msg, boolean isPart, Object... args) {
        if (!mLogConfig.isEnable() || !LogUtils.configAllowLog) {
            return;
        }
        if (type < mLogConfig.getLogLevel()) {
            return;
        }
        String tag = generateTag(element);
        if (msg.length() > CommonUtil.LINE_MAX) {
            tag = "LogUtils";
            printLog(type, tag, printDividingLine(DIR_TOP));
            printLog(type, tag, printDividingLine(DIR_NORMAL) + generateTag(element));
            printLog(type, tag, printDividingLine(DIR_CENTER));
            for (String subMsg : CommonUtil.largeStringToList(msg)) {
                logString(type, element, subMsg, true, args);
            }
            printLog(type, tag, printDividingLine(DIR_BOTTOM));
            return;
        }
        if (args.length > 0) {
            try {
                msg = String.format(msg, args);
            } catch (MissingFormatArgumentException e) {

            }
        }
        if (mLogConfig.isShowBorder()) {
            tag = "LogUtils";
            if (isPart) {
                for (String sub : msg.split(LINE_SEPARATOR)) {
                    printLog(type, tag, printDividingLine(DIR_NORMAL) + sub);
                }
            } else {
                printLog(type, tag, printDividingLine(DIR_TOP));
                printLog(type, tag, printDividingLine(DIR_NORMAL) + generateTag(element));
                printLog(type, tag, printDividingLine(DIR_CENTER));
                for (String sub : msg.split(LINE_SEPARATOR)) {
                    printLog(type, tag, printDividingLine(DIR_NORMAL) + sub);
                }
                printLog(type, tag, printDividingLine(DIR_BOTTOM));
            }
        } else {
            printLog(type, tag, msg);
        }
    }


    /**
     * 打印分割线
     *
     * @param dir
     * @return
     */
    public static String printDividingLine(int dir) {
        switch (dir) {
            case DIR_TOP:
                return "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════";
            case DIR_BOTTOM:
                return "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════";
            case DIR_NORMAL:
                return "║ ";
            case DIR_CENTER:
                return "╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────";
            default:
                break;
        }
        return "";
    }

    /**
     * 打印对象
     *
     * @param type
     * @param element
     * @param object
     */
    private void logObject(@LogLevelType int type, StackTraceElement element, Object object) {
        if (object != null) {
            for (Parser parser : mLogConfig.getParseList()) {
                if (parser.parseClassType().isAssignableFrom(object.getClass())) {
                    logString(type, element, parser.parseString(object));
                    return;
                }
            }
        }
        logString(type, element, CommonUtil.objectToString(object));
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
        tag = String.format(tag, mLogConfig.getTagPrefix(), callerClazzName,
                caller.getMethodName(), stackTrace);
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

}
