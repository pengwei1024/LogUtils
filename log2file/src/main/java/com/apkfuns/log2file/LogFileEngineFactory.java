package com.apkfuns.log2file;

import android.util.Log;

import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.file.LogFileEngine;
import com.apkfuns.logutils.file.LogFileParam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import okio.BufferedSink;
import okio.Okio;
import okio.Sink;

/**
 * Created by pengwei on 2017/3/30.
 */

public class LogFileEngineFactory implements LogFileEngine {

    private static final String TAG = "LogFileEngineFactory";
    private static final String FORMAT = "[%s][%s][%s:%s]%s\n";
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    private Sink sink;
    private BufferedSink bSink = null;
    private File logFile;

    @Override
    public synchronized void writeToFile(File logFile, String logContent, LogFileParam params) {
        if (!ensureSinkCreate(logFile, false)) {
            return;
        }
        String msg = getWriteString(logContent, params);
        try {
            bSink.writeUtf8(msg);
            bSink.flush();
            System.out.println(msg);
        } catch (IOException e) {
            Log.e(TAG, "Sink.writeUtf8 Error and retry once", e);
            ensureSinkCreate(logFile, true);
            try {
                bSink.writeUtf8(msg);
                bSink.flush();
            } catch (IOException e1) {
                Log.e(TAG, e1.getMessage(), e1);
            }
        }
    }

    /**
     * 写入文件的内容
     *
     * @param logContent
     * @param params
     * @return
     */
    private String getWriteString(String logContent, LogFileParam params) {
        String time = dateFormat.format(new Date(params.getTime()));
        return String.format(FORMAT, time, getLogLevelString(params.getLogLevel()),
                params.getThreadName(), params.getTagName(), logContent);
    }

    /**
     * 日志等级
     *
     * @param level
     * @return
     */
    private String getLogLevelString(int level) {
        switch (level) {
            case LogLevel.TYPE_VERBOSE:
                return "V";
            case LogLevel.TYPE_ERROR:
                return "E";
            case LogLevel.TYPE_INFO:
                return "I";
            case LogLevel.TYPE_WARM:
                return "W";
            case LogLevel.TYPE_WTF:
                return "Wtf";
        }
        return "D";
    }


    /**
     * 关闭sink流
     */
    private void close() {
        try {
            if (sink != null) {
                sink.close();
                sink = null;
            }
            if (bSink != null) {
                bSink.close();
                bSink = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 确保sink创建
     *
     * @param logFile
     * @param force
     */
    private boolean ensureSinkCreate(File logFile, boolean force) {
        if (logFile == null) {
            return false;
        }
        if (!logFile.exists()) {
            if (logFile.getParentFile() != null && !logFile.getParentFile().exists()) {
                logFile.getParentFile().mkdirs();
            }
        }
        if (force) {
            this.logFile = null;
        }
        if (this.logFile == null || !this.logFile.getPath().equals(logFile.getPath())
                || sink == null || bSink == null) {
            this.logFile = logFile;
            close();
            try {
                sink = Okio.appendingSink(logFile);
                bSink = Okio.buffer(sink);
            } catch (FileNotFoundException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
        return true;
    }
}
