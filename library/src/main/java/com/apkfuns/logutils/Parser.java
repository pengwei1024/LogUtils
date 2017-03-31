package com.apkfuns.logutils;

/**
 * Created by pengwei on 16/3/8.
 * 格式化对象
 */
public interface Parser<T> {

    String LINE_SEPARATOR = Constant.BR;

    Class<T> parseClassType();

    String parseString(T t);
}
