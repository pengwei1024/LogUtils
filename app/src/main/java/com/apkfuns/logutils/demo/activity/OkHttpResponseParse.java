package com.apkfuns.logutils.demo.activity;

import com.apkfuns.logutils.Parser;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Response;

/**
 * Created by pengwei on 16/3/10.
 */
public class OkHttpResponseParse implements Parser<Response> {
    @Override
    public Class<Response> parseClassType() {
        return Response.class;
    }

    @Override
    public String parseString(Response response) {
        if (response != null) {
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("code = %s" + LINE_SEPARATOR, response.code()));
            builder.append(String.format("isSuccessful = %s" + LINE_SEPARATOR, response.isSuccessful()));
            builder.append(String.format("url = %s" + LINE_SEPARATOR, response.request().url()));
            builder.append(String.format("message = %s" + LINE_SEPARATOR, response.message()));
            builder.append(String.format("protocol = %s" + LINE_SEPARATOR, response.protocol()));
            builder.append(String.format("header = %s" + LINE_SEPARATOR,
                    new HeaderParse().parseString(response.headers())));
            try {
                builder.append(String.format("body = %s" + LINE_SEPARATOR, response.body().string()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return builder.toString();
        }
        return null;
    }
}
