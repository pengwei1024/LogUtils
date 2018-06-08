package com.apkfuns.logutils.demo.utils;

import com.apkfuns.logutils.LogUtils;

public class ThreadLog extends Thread {
    private String logMsg;

    public ThreadLog(String threadName, String logMsg) {
        super(threadName);
        this.logMsg = logMsg;
    }

    @Override
    public void run() {
        LogUtils.d("name=%s, content=%s", getName(), logMsg);
    }
}
