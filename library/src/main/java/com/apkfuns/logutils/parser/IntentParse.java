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
        StringBuilder builder = new StringBuilder(parseClassType().getSimpleName() + " [" + LINE_SEPARATOR);
        builder.append(String.format("%s = %s" + LINE_SEPARATOR, "Scheme", intent.getScheme()));
        builder.append(String.format("%s = %s" + LINE_SEPARATOR, "Action", intent.getAction()));
        builder.append(String.format("%s = %s" + LINE_SEPARATOR, "DataString", intent.getDataString()));
        builder.append(String.format("%s = %s" + LINE_SEPARATOR, "Type", intent.getType()));
        builder.append(String.format("%s = %s" + LINE_SEPARATOR, "Package", intent.getPackage()));
        builder.append(String.format("%s = %s" + LINE_SEPARATOR, "ComponentInfo", intent.getComponent()));
        builder.append(String.format("%s = %s" + LINE_SEPARATOR, "Flags", intent.getFlags()));
        builder.append(String.format("%s = %s" + LINE_SEPARATOR, "Categories", intent.getCategories()));
        builder.append(String.format("%s = %s" + LINE_SEPARATOR, "Bundle",
                new BundleParse().parseString(intent.getExtras())));
        return builder.toString() + "]";
    }
}
