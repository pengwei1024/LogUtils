package com.apkfuns.logutils.parser;

import android.os.Message;

import com.apkfuns.logutils.Parser;
import com.apkfuns.logutils.utils.ObjectUtil;

/**
 * Created by pengwei on 2017/3/29.
 */

public class MessageParse implements Parser<Message> {
    @Override
    public Class<Message> parseClassType() {
        return Message.class;
    }

    @Override
    public String parseString(Message message) {
        if (message == null) {
            return "null";
        }
        StringBuilder stringBuilder = new StringBuilder(message.getClass().getName() + " [" + LINE_SEPARATOR);
        stringBuilder.append(String.format("%s = %s", "what", message.what)).append(LINE_SEPARATOR);
        stringBuilder.append(String.format("%s = %s", "when", message.getWhen())).append(LINE_SEPARATOR);
        stringBuilder.append(String.format("%s = %s", "arg1", message.arg1)).append(LINE_SEPARATOR);
        stringBuilder.append(String.format("%s = %s", "arg2", message.arg2)).append(LINE_SEPARATOR);
        stringBuilder.append(String.format("%s = %s", "data",
                new BundleParse().parseString(message.getData()))).append(LINE_SEPARATOR);
        stringBuilder.append(String.format("%s = %s", "obj",
                ObjectUtil.objectToString(message.obj))).append(LINE_SEPARATOR);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
