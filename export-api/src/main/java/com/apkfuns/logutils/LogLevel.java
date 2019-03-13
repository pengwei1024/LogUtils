package com.apkfuns.logutils;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by pengwei on 16/3/3.
 */
public class LogLevel {
    public static final int TYPE_VERBOSE = 1;
    public static final int TYPE_DEBUG = 2;
    public static final int TYPE_INFO = 3;
    public static final int TYPE_WARM = 4;
    public static final int TYPE_ERROR = 5;
    public static final int TYPE_WTF = 6;

    @IntDef({TYPE_VERBOSE, TYPE_DEBUG, TYPE_INFO, TYPE_WARM, TYPE_ERROR, TYPE_WTF})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LogLevelType {
    }
}
