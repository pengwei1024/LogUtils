package com.apkfuns.logutils;

import com.apkfuns.logutils.parser.ActivityParse;
import com.apkfuns.logutils.parser.BundleParse;
import com.apkfuns.logutils.parser.CollectionParse;
import com.apkfuns.logutils.parser.IntentParse;
import com.apkfuns.logutils.parser.MapParse;
import com.apkfuns.logutils.parser.MessageParse;
import com.apkfuns.logutils.parser.ReferenceParse;
import com.apkfuns.logutils.parser.ThrowableParse;

import java.util.List;

/**
 * Created by pengwei on 16/4/18.
 */
public class Constant {

    public static final String STRING_OBJECT_NULL = "Object[object is null]";

    // 每行最大日志长度 (Android Studio3.1最多2902字符)
    public static final int LINE_MAX = 2800;

    // 解析属性最大层级
    public static final int MAX_CHILD_LEVEL = 2;

    public static final int MIN_STACK_OFFSET = 5;

    // 换行符
    public static final String BR = System.getProperty("line.separator");

    // 默认支持解析库
    public static final Class<? extends Parser>[] DEFAULT_PARSE_CLASS = new Class[]{
            BundleParse.class, IntentParse.class, CollectionParse.class,
            MapParse.class, ThrowableParse.class, ReferenceParse.class, MessageParse.class,
            ActivityParse.class
    };

    // 字符表
    static final String[] CHARACTER_TABLE = new String[]{
            "➀", "➁", "➂", "➃", "➄", "➅", "➆", "➇", "➈", "➉"
    };


    /**
     * 获取默认解析类
     *
     * @return
     */
    public static List<Parser> getParsers() {
        return LogConfigImpl.getInstance().getParseList();
    }
}
