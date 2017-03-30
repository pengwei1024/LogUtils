package com.apkfuns.logutils.demo.parse;

import com.apkfuns.logutils.Parser;

import java.util.Collection;

/**
 * Created by pengwei on 16/3/12.
 */
public class CollectParse implements Parser<Collection> {
    @Override
    public Class<Collection> parseClassType() {
        return Collection.class;
    }

    @Override
    public String parseString(Collection collection) {
        return collection.toString();
    }
}
