package com.apkfuns.logutils.demo.activity;

import com.apkfuns.logutils.Parser;

import okhttp3.Headers;

/**
 * Created by pengwei on 16/3/10.
 */
public class HeaderParse implements Parser<Headers> {
    @Override
    public Class<Headers> parseClassType() {
        return Headers.class;
    }

    @Override
    public String parseString(Headers headers) {
        StringBuilder builder = new StringBuilder(headers.getClass().getSimpleName() + " [" + LINE_SEPARATOR);
        for (String name : headers.names()) {
            builder.append(String.format("%s = %s" + LINE_SEPARATOR,
                    name, headers.get(name)));
        }
        return builder.append("]").toString();
    }
}
