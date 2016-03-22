package com.apkfuns.logutils.utils;


import com.apkfuns.logutils.parser.ReferenceParse;

import java.lang.ref.Reference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengwei08 on 2015/7/20.
 */
public class CommonUtil {

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    // 分割线方位
    public static final int DIR_TOP = 1;
    public static final int DIR_BOTTOM = 2;
    public static final int DIR_CENTER = 4;
    public static final int DIR_NORMAL = 3;

    // 基本数据类型
    public static final String[] TYPES = {"int", "java.lang.String", "boolean", "char",
            "float", "double", "long", "short", "byte"};

    public static final int LINE_MAX = 2048;

    /**
     * 获取StackTraceElement对象
     *
     * @return
     */
    public static StackTraceElement getStackTrace() {
        return Thread.currentThread().getStackTrace()[4];
    }

    /**
     * 将对象转化为String
     *
     * @param object
     * @return
     */
    // TODO: 16/3/12 对象包含复杂对象
    public static String objectToString(Object object) {
        if (object == null) {
            return "Object[object is null]";
        }
        if (ArrayUtil.isArray(object)) {
            return ArrayUtil.parseArray(object);
        } else if (object instanceof Reference) {
            return new ReferenceParse().parseString((Reference) object);
        } else {
            if (object.toString().startsWith(object.getClass().getName() + "@")) {
                StringBuilder builder = new StringBuilder();
                getClassFields(object.getClass(), builder, object, false);
                Class superClass = object.getClass().getSuperclass();
                while (superClass != null) {
                    getClassFields(superClass, builder, object, true);
                    superClass = superClass.getSuperclass();
                }
                return builder.toString();
            } else {
                // 若对象重写toString()方法默认走toString()
                return object.toString();
            }
        }
    }

    /**
     * 拼接class的字段和值
     *
     * @param cla
     * @param builder
     */
    private static void getClassFields(Class cla, StringBuilder builder, Object o, boolean isSuper) {
        if (cla.equals(Object.class)) {
            return;
        }
        if (isSuper) {
            builder.append(LINE_SEPARATOR + "=> ");
        }
        builder.append(cla.getSimpleName() + " {");
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object subObject = null;
            try {
                subObject = field.get(o);
            } catch (IllegalAccessException e) {
                subObject = e;
            } finally {
                if (subObject != null) {
                    if (subObject instanceof String) {
                        subObject = "\"" + subObject + "\"";
                    } else if (subObject instanceof Character) {
                        subObject = "\'" + subObject + "\'";
                    }
                }
                builder.append(String.format("%s = %s, ", field.getName(),
                        subObject == null ? "null" : subObject.toString()));
            }
        }
        if (builder.toString().endsWith("{")) {
            builder.append("}");
        } else {
            builder.replace(builder.length() - 2, builder.length() - 1, "}");
        }
    }

    /**
     * 长字符串转化为List
     *
     * @param msg
     * @return
     */
    public static List<String> largeStringToList(String msg) {
        List<String> stringList = new ArrayList<>();
        int index = 0;
        int maxLength = LINE_MAX;
        int countOfSub = msg.length() / maxLength;
        if (countOfSub > 0) {
            for (int i = 0; i < countOfSub; i++) {
                String sub = msg.substring(index, index + maxLength);
                stringList.add(sub);
                index += maxLength;
            }
            stringList.add(msg.substring(index, msg.length()));
        } else {
            stringList.add(msg);
        }
        return stringList;
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

}
