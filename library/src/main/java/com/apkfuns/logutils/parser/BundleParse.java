package com.apkfuns.logutils.parser;

import android.os.Bundle;

import com.apkfuns.logutils.Parser;
import com.apkfuns.logutils.utils.CommonUtil;

/**
 * Created by pengwei on 16/3/8.
 */
public class BundleParse implements Parser<Bundle> {

    @Override
    public Class<Bundle> parseClassType() {
        return Bundle.class;
    }

    @Override
    public String parseString(Bundle bundle) {
        StringBuilder builder = new StringBuilder(bundle.getClass().getName() + " [\n");
        for (String key : bundle.keySet()) {
            builder.append(String.format("'%s' => %s \n", key, CommonUtil.objectToString(bundle.get(key))));
        }
        builder.append("]\n");
        return builder.toString();
    }
}
