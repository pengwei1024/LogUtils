package com.apkfuns.logutils.utils;

import com.apkfuns.logutils.Apis;
import com.apkfuns.logutils.Constant;
import com.apkfuns.logutils.Parser;
import com.apkfuns.logutils.parser.ReferenceParse;
import com.apkfuns.logutils.utils.ArrayUtil;

import java.lang.ref.Reference;
import java.lang.reflect.Field;
import java.util.List;

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
        if (object == null) {
            return Constant.STRING_OBJECT_NULL;
        }
        if (Apis.getParsers() != null && Apis.getParsers().size() > 0) {
            for (Parser parser : Apis.getParsers()) {
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
            getClassFields(object.getClass(), builder, object, false);
            Class superClass = object.getClass().getSuperclass();
            while (!superClass.equals(Object.class)) {
                getClassFields(superClass, builder, object, true);
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
     */
    private static void getClassFields(Class cla, StringBuilder builder, Object o, boolean isSubClass) {
        if (cla.equals(Object.class)) {
            return;
        }
        if (isSubClass) {
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
                    // TODO: 16/4/20 考虑死循环的问题 
//                    if (isRelated(cla, subObject.getClass())) {
                        subObject = objectToString(subObject);
//                    }
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
     * 两个类是否相关
     * 避免死循环
     *
     * @param cla1
     * @param cla2
     */
    public static boolean isRelated(Class cla1, Class cla2) {
        return cla1.isAssignableFrom(cla2) || cla2.isAssignableFrom(cla1);
    }

}
