package com.apkfuns.logutils.utils;

import com.apkfuns.logutils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengwei on 16/4/19.
 */
public class Utils {

    // 分割线方位
    public static final int DIVIDER_TOP = 1;
    public static final int DIVIDER_BOTTOM = 2;
    public static final int DIVIDER_CENTER = 4;
    public static final int DIVIDER_NORMAL = 3;

    /**
     * 打印分割线
     *
     * @param dir
     * @return
     */
    public static String printDividingLine(int dir) {
        switch (dir) {
            case DIVIDER_TOP:
                return "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════";
            case DIVIDER_BOTTOM:
                return "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════";
            case DIVIDER_NORMAL:
                return "║ ";
            case DIVIDER_CENTER:
                return "╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────";
            default:
                break;
        }
        return "";
    }


    /**
     * 长字符串转化为List
     *
     * @param msg
     * @return
     */
    public static List<String> largeStringToList(String msg) {
        List<String> stringList = new ArrayList<>();
        int index = 0;
        int maxLength = Constant.LINE_MAX;
        int countOfSub = msg.length() / maxLength;
        if (countOfSub > 0) {
            for (int i = 0; i < countOfSub; i++) {
                String sub = msg.substring(index, index + maxLength);
                stringList.add(sub);
                index += maxLength;
            }
            stringList.add(msg.substring(index, msg.length()));
        } else {
            stringList.add(msg);
        }
        return stringList;
    }

    public static String shorten(String string, int count, int length) {
        if (string == null) return null;
        String resultString = string;
        if (Math.abs(length) < resultString.length()) {
            if (length > 0)
                resultString = string.substring(0, length);
            if (length < 0)
                resultString = string.substring(string.length() + length, string.length());
        }
        if (Math.abs(count) > resultString.length()) {
            return String.format("%" + count + "s", resultString);
        }
        return resultString;
    }

    public static String shortenClassName(String className, int count, int maxLength) {
        className = shortenPackagesName(className, count);
        if (className == null) return null;
        if (maxLength == 0) return className;
        if (maxLength > className.length()) return className;
        if (maxLength < 0) {
            maxLength = -maxLength;
            StringBuilder builder = new StringBuilder();
            for (int index = className.length() - 1; index > 0; ) {
                int i = className.lastIndexOf('.', index);
                if (i == -1) {
                    if (builder.length() > 0
                            && builder.length() + index + 1 > maxLength) {
                        builder.insert(0, '*');
                        break;
                    }
                    builder.insert(0, className.substring(0, index + 1));
                } else {
                    if (builder.length() > 0
                            && builder.length() + (index + 1 - i) + 1 > maxLength) {
                        builder.insert(0, '*');
                        break;
                    }
                    builder.insert(0, className.substring(i, index + 1));
                }
                index = i - 1;
            }
            return builder.toString();
        } else {
            StringBuilder builder = new StringBuilder();
            for (int index = 0; index < className.length(); ) {
                int i = className.indexOf('.', index);
                if (i == -1) {
                    if (builder.length() > 0) {
                        builder.insert(builder.length(), '*');
                        break;
                    }
                    builder.insert(builder.length(), className.substring(index, className.length()));
                    break;
                } else {
                    if (builder.length() > 0
                            && i + 1 > maxLength) {
                        builder.insert(builder.length(), '*');
                        break;
                    }

                    builder.insert(builder.length(), className.substring(index, i + 1));
                }

                index = i + 1;
            }
            return builder.toString();
        }
    }

    // todo optimize it
    private static String shortenPackagesName(String className, int count) {
        if (className == null) return null;
        if (count == 0) return className;
        StringBuilder builder = new StringBuilder();
        if (count > 0) {
            int points = 1;
            for (int index = 0; index < className.length(); ) {
                int i = className.indexOf('.', index);
                if (i == -1) {
                    builder.insert(builder.length(), className.substring(index, className.length()));
                    break;
                } else {
                    if (points == count) {
                        builder.insert(builder.length(), className.substring(index, i));
                        break;
                    }
                    builder.insert(builder.length(), className.substring(index, i + 1));
                }
                index = i + 1;
                points++;
            }
        } else if (count < 0) {
            String exceptString = shortenPackagesName(className, -count);
            if (className.equals(exceptString)) {
                int from = className.lastIndexOf('.') + 1;
                int to = className.length();
                builder.insert(builder.length(), className.substring(from, to));
            } else
                return className.replaceFirst(exceptString + '.', "");
        }
        return builder.toString();
    }


}
