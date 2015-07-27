package com.apkfuns.logutils.utils;

import java.util.Arrays;

/**
 * Created by zhutiantao on 2015/7/27.
 */
public class ArrayUtilNew  {

    private static boolean isArray(Object object) {
        return object.getClass().getName().startsWith("[");
    }
    public static String traverseArray(Object object) {
        StringBuilder result = new StringBuilder();
        traverseArray(result,object);
        return result.toString();
    }
    private static void  traverseArray(StringBuilder result,Object object) {
        if(!isArray(object)) {
            result.append(object.toString());
            return;
        }
        if(isSingleArray(object)){
            switch (getType(object)) {
                case 'I':
                    result.append(Arrays.toString((int[]) object)).append("\n");
                    return;
                case 'D':
                    result.append(Arrays.toString((double[]) object)).append("\n");
                    return;
                case 'Z':
                    result.append(Arrays.toString((boolean[]) object)).append("\n");
                    return;
                case 'B':
                    result.append(Arrays.toString((byte[]) object)).append("\n");
                    return;
                case 'S':
                    result.append(Arrays.toString((short[]) object)).append("\n");
                    return;
                case 'J':
                    result.append(Arrays.toString((long[]) object)).append("\n");
                    return;
                case 'F':
                    result.append(Arrays.toString((float[]) object)).append("\n");
                    return;
                case 'L':
                    result.append(Arrays.toString((Object[]) object)).append("\n");
                default:
                    return;
            }
        }
        for(int i=0;i<((Object[]) object).length;i++) {
            traverseArray(result, ((Object[]) object)[i]);
        }
    }

    private static char getType(Object object) {
        String s = object.getClass().getName();
        for(int i=0; i<s.length();i++) {
            if(s.charAt(i)=='[') {
                continue;
            }
            return s.charAt(i);
        }
        return 0;
    }

    private static boolean isSingleArray(Object object) {
        return object.getClass().getName().lastIndexOf("[") == object.getClass().getName().indexOf("[");
    }
}
