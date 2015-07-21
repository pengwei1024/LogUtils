package com.apkfuns.logutils.utils;

import java.lang.reflect.Field;

/**
 * Created by pengwei08 on 2015/7/20.
 */
public class SystemUtil {
    /**
     * 获取StackTraceElement对象
     * @return
     */
    public static StackTraceElement getStackTrace(){
        return Thread.currentThread().getStackTrace()[4];
    }


    // 基本数据类型
    private final static String[] types = {"int", "java.lang.String", "boolean", "char",
            "float", "double", "long", "short", "byte"};

    /**
     * 将对象转化为String
     *
     * @param object
     * @return
     */
    public static <T> String objectToString(T object) {
        if (object == null) {
            return "Null{object is null}";
        }
        if (object.toString().startsWith(object.getClass().getName() + "@")) {
            StringBuilder builder = new StringBuilder(object.getClass().getSimpleName() + "{");
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                for (String type : types) {
                    if (field.getType().getName().equalsIgnoreCase(type)) {
                        Object value = null;
                        try {
                            value = field.get(object);
                        } catch (IllegalAccessException e) {
                            value = e;
                        }finally {
                            builder.append(String.format("%s=%s, ", field.getName(),
                                    value == null ? "null" : value.toString()));
                            break;
                        }
                    }
                }
            }
            return builder.replace(builder.length() - 2, builder.length() - 1, "}").toString();
        } else {
            return object.toString();
        }
    }

}
