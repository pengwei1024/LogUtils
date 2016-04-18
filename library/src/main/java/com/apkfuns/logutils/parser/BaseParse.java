package com.apkfuns.logutils.parser;

import com.apkfuns.logutils.Parser;

/**
 * Created by pengwei on 16/4/18.
 */
public abstract class BaseParse<T> implements Parser<T> {

    private Class<T> cla;

    @Override
    public Class<T> parseClassType() {
        return cla;
    }

    @Override
    public String parseString(T t) {
        cla = (Class<T>) t.getClass();
        StringBuilder builder = new StringBuilder(t.getClass().getSimpleName() + " [" + LINE_SEPARATOR);
        parseString(t, builder);
        builder.append("]");
        return builder.toString();
    }

    public abstract void parseString(T t, StringBuilder builder);
}
