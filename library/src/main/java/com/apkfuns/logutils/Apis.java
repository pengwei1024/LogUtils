package com.apkfuns.logutils;

import java.util.List;

/**
 * Created by pengwei on 16/4/19.
 */
// TODO: 16/4/19 为了解决Utils不能访问LogConfigImpl的问题, 有更好方案？
public class Apis {

    /**
     * 获取默认解析类
     *
     * @return
     */
    public static List<Parser> getParsers() {
        return LogConfigImpl.getInstance().getParseList();
    }
}
