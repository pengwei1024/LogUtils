package com.apkfuns.logutils.parser;

import com.apkfuns.logutils.Parser;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析器管理类
 */
public class ParserManager {

    private List<Parser> parseList;
    private volatile static ParserManager singleton;

    private ParserManager() {
        this.parseList = new ArrayList<>();
    }

    public static ParserManager getInstance() {
        if (singleton == null) {
            synchronized (ParserManager.class) {
                if (singleton == null) {
                    singleton = new ParserManager();
                }
            }
        }
        return singleton;
    }

    public synchronized void addParserClass(Class<? extends Parser>... classes) {
        for (Class<? extends Parser> cla : classes) {
            try {
                parseList.add(0, cla.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Parser> getParseList() {
        return parseList;
    }
}
