package com.apkfuns.logutils.parser;

import android.content.Intent;
import android.text.TextUtils;

import com.apkfuns.logutils.Parser;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pengwei on 16/3/8.
 */
public class IntentParse implements Parser<Intent> {

    private static Map<Integer, String> flagMap = new HashMap<>();

    static {
        Class cla = Intent.class;
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().startsWith("FLAG_")) {
                int value = 0;
                try {
                    Object object = field.get(cla);
                    if (object instanceof Integer || object.getClass().getSimpleName().equals("int")) {
                        value = (int) object;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (flagMap.get(value) == null) {
                    flagMap.put(value, field.getName());
                }
            }
        }
    }

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
        builder.append(String.format("%s = %s" + LINE_SEPARATOR, "Flags", getFlags(intent.getFlags())));
        builder.append(String.format("%s = %s" + LINE_SEPARATOR, "Categories", intent.getCategories()));
        builder.append(String.format("%s = %s" + LINE_SEPARATOR, "Extras",
                new BundleParse().parseString(intent.getExtras())));
        return builder.toString() + "]";
    }

    /**
     * 获取flag的值
     * 感谢涛哥提供的方法(*^__^*)
     *
     * @param flags
     * @return
     */
    private String getFlags(int flags) {
        StringBuilder builder = new StringBuilder();
        for (int flagKey : flagMap.keySet()) {
            if ((flagKey & flags) == flagKey) {
                builder.append(flagMap.get(flagKey));
                builder.append(" | ");
            }
        }
        if (TextUtils.isEmpty(builder.toString())) {
            builder.append(flags);
        } else if (builder.indexOf("|") != -1) {
            builder.delete(builder.length() - 2, builder.length());
        }
        return builder.toString();
    }
}
