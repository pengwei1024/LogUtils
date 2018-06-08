package com.apkfuns.logutils.parser;

import android.app.Activity;
import com.apkfuns.logutils.Parser;
import com.apkfuns.logutils.utils.ObjectUtil;

import java.lang.reflect.Field;

public class ActivityParse implements Parser<Activity> {
    @Override
    public Class<Activity> parseClassType() {
        return Activity.class;
    }

    @Override
    public String parseString(Activity activity) {
        if (activity == null) {
            return "null";
        }
        Field[] fields = activity.getClass().getDeclaredFields();
        StringBuilder builder = new StringBuilder(activity.getClass().getName());
        builder.append(" {");
        builder.append(LINE_SEPARATOR);
        for (Field field : fields) {
            field.setAccessible(true);
            if ("org.aspectj.lang.JoinPoint$StaticPart".equals(field.getType().getName())) {
                continue;
            }
            if (field.getName().equals("$change") || field.getName().equalsIgnoreCase("this$0")) {
                continue;
            }
            try {
                Object fieldValue = field.get(activity);
                builder.append(field.getName()).append("=>")
                        .append(ObjectUtil.objectToString(fieldValue)).append(LINE_SEPARATOR);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        builder.append("}");
        return builder.toString();
    }
}
