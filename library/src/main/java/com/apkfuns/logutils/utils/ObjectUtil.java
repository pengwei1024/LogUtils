package com.apkfuns.logutils.utils;

import android.text.TextUtils;

import com.apkfuns.logutils.Constant;
import com.apkfuns.logutils.Parser;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static com.apkfuns.logutils.Constant.*;

/**
 * Created by pengwei08 on 2015/7/20.
 */
public class ObjectUtil {

    /**
     * 将对象转化为String
     *
     * @param object
     * @return
     */
    public static String objectToString(Object object) {
        return objectToString(object, 0);
    }

    /**
     * 是否为静态内部类
     *
     * @param cla
     * @return
     */
    public static boolean isStaticInnerClass(Class cla) {
        if (cla != null && cla.isMemberClass()) {
            int modifiers = cla.getModifiers();
            if ((modifiers & Modifier.STATIC) == Modifier.STATIC) {
                return true;
            }
        }
        return false;
    }

    public static String objectToString(Object object, int childLevel) {
        if (object == null) {
            return Constant.STRING_OBJECT_NULL;
        }
        if (childLevel > Constant.MAX_CHILD_LEVEL) {
            return object.toString();
        }
        if (Constant.getParsers() != null && Constant.getParsers().size() > 0) {
            for (Parser parser : Constant.getParsers()) {
                if (parser.parseClassType().isAssignableFrom(object.getClass())) {
                    return parser.parseString(object);
                }
            }
        }
        if (ArrayUtil.isArray(object)) {
            return ArrayUtil.parseArray(object);
        }
        if (object.toString().startsWith(object.getClass().getName() + "@")) {
            StringBuilder builder = new StringBuilder();
            getClassFields(object.getClass(), builder, object, false, childLevel);
            Class superClass = object.getClass().getSuperclass();
            while (!superClass.equals(Object.class)) {
                getClassFields(superClass, builder, object, true, childLevel);
                superClass = superClass.getSuperclass();
            }
            return builder.toString();
        } else {
            // 若对象重写toString()方法默认走toString()
            return object.toString();
        }
    }

    /**
     * 拼接class的字段和值
     *
     * @param cla
     * @param builder
     * @param o           对象
     * @param isSubClass  死否为子class
     * @param childOffset 递归解析属性的层级
     */
    private static void getClassFields(Class cla, StringBuilder builder, Object o, boolean isSubClass,
                                       int childOffset) {
        if (cla.equals(Object.class)) {
            return;
        }
        if (isSubClass) {
            builder.append(BR + BR + "=> ");
        }
//        String breakLine = childOffset == 0 ? BR : "";
        String breakLine = "";
        builder.append(cla.getSimpleName() + " {");
        Field[] fields = cla.getDeclaredFields();
        for (int i = 0; i < fields.length; ++i) {
            Field field = fields[i];
            field.setAccessible(true);
            if (cla.isMemberClass() && !isStaticInnerClass(cla) && i == 0) {
                continue;
            }
            Object subObject = null;
            try {
                subObject = field.get(o);
            } catch (IllegalAccessException e) {
                subObject = e;
            } finally {
                if (subObject != null) {
                    // 解决Instant Run情况下内部类死循环的问题
                    System.out.println(field.getName()+ "***" +subObject.getClass() + "啊啊啊啊啊啊" + cla);
                    if (!isStaticInnerClass(cla) && (field.getName().equals("$change") || field.getName().equalsIgnoreCase("this$0"))) {
                        continue;
                    }
                    if (subObject instanceof String) {
                        subObject = "\"" + subObject + "\"";
                    } else if (subObject instanceof Character) {
                        subObject = "\'" + subObject + "\'";
                    }
                    if (childOffset < Constant.MAX_CHILD_LEVEL) {
                        subObject = objectToString(subObject, childOffset + 1);
                    }
                }
                String formatString = breakLine + "%s = %s, ";
                System.out.println(field.getName() + "**" + cla.getName() + "**" + isSubClass + "**" + o.toString());
                builder.append(String.format(formatString, field.getName(),
                        subObject == null ? "null" : subObject.toString()));
            }
        }
        if (builder.toString().endsWith("{")) {
            builder.append("}");
        } else {
            builder.replace(builder.length() - 2, builder.length() - 1, breakLine + "}");
        }
    }
}
