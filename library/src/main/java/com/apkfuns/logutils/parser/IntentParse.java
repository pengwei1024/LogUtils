package com.apkfuns.logutils.parser;

import android.content.Intent;

import com.apkfuns.logutils.Parser;

/**
 * Created by pengwei on 16/3/8.
 */
public class IntentParse implements Parser<Intent> {
    @Override
    public Class<Intent> parseClassType() {
        return Intent.class;
    }

    @Override
    public String parseString(Intent intent) {
        return intent.toString();
    }
}
