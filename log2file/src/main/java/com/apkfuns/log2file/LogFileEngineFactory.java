package com.apkfuns.log2file;

import com.apkfuns.logutils.file.LogFileEngine;
import com.apkfuns.logutils.file.LogFileParam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import okio.BufferedSink;
import okio.Okio;
import okio.Sink;

/**
 * Created by pengwei on 2017/3/30.
 */

public class LogFileEngineFactory implements LogFileEngine {

    private Sink sink;
    private BufferedSink bSink = null;
    private File logFile;

    @Override
    public synchronized void writeToFile(File logFile, String logContent, LogFileParam params) {
        if (logFile != null && sink != null && bSink != null
                && this.logFile.getPath().equals(logFile.getPath())) {
            try {
                bSink.writeUtf8(logContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createSink(File logFile) {
        if (logFile == null || !logFile.exists()) {
            return;
        }
        try {
            if (sink != null) {
                sink.close();
            }
            if (bSink != null) {
                bSink.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            sink = Okio.appendingSink(logFile);
            bSink = Okio.buffer(sink);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
