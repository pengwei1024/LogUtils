package com.apkfuns.logutils.parser;

import com.apkfuns.logutils.Parser;
import com.apkfuns.logutils.utils.CommonUtil;

import java.util.Map;
import java.util.Set;

/**
 * Created by pengwei on 16/3/8.
 */
public class MapParse implements Parser<Map> {
    @Override
    public Class<Map> parseClassType() {
        return Map.class;
    }

    @Override
    public String parseString(Map map) {
        String msg = map.getClass().getName() + " [\n";
        Set<Object> keys = map.keySet();
        for (Object key : keys) {
            String itemString = "%s -> %s\n";
            Object value = map.get(key);
            msg += String.format(itemString, CommonUtil.objectToString(key),
                    CommonUtil.objectToString(value));
        }
        return msg + "]";
    }
}
