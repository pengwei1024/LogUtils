package com.apkfuns.logutils;

import com.apkfuns.logutils.parser.BundleParse;
import com.apkfuns.logutils.parser.CollectionParse;
import com.apkfuns.logutils.parser.IntentParse;
import com.apkfuns.logutils.parser.MapParse;
import com.apkfuns.logutils.parser.ReferenceParse;
import com.apkfuns.logutils.parser.ThrowableParse;

/**
 * Created by pengwei on 16/4/18.
 */
public interface Constant {

    String STRING_OBJECT_NULL = "Object[object is null]";

    // 每行最大日志长度
    int LINE_MAX = 1024 * 3;

    // 换行符
    String LINE_SEPARATOR = System.getProperty("line.separator");

    // 默认支持解析库
    Class<? extends Parser>[] DEFAULT_PARSE_CLASS = new Class[]{
            BundleParse.class, IntentParse.class, CollectionParse.class,
            MapParse.class, ThrowableParse.class, ReferenceParse.class
    };
}
