package com.apkfuns.logutils;

import android.os.Environment;
import android.text.TextUtils;

import com.apkfuns.logutils.file.LogFileEngine;
import com.apkfuns.logutils.file.LogFileFilter;
import com.apkfuns.logutils.pattern.LogPattern;

import java.io.File;

/**
 * Created by pengwei on 2017/3/30.
 */

class Log2FileConfigImpl implements Log2FileConfig {

    public static final String DEFAULT_LOG_NAME_FORMAT = "%d{yyyyMMdd}.txt";

    private LogFileEngine engine;
    private LogFileFilter fileFilter;
    @LogLevel.LogLevelType
    private int logLevel = LogLevel.TYPE_VERBOSE;
    private boolean enable = false;
    private String logFormatName = DEFAULT_LOG_NAME_FORMAT;
    private String logPath;
    private static Log2FileConfigImpl singleton;

    public static Log2FileConfigImpl getInstance() {
        if (singleton == null) {
            synchronized (Log2FileConfigImpl.class) {
                if (singleton == null) {
                    singleton = new Log2FileConfigImpl();
                }
            }
        }
        return singleton;
    }

    @Override
    public Log2FileConfig configLog2FileEnable(boolean enable) {
        this.enable = enable;
        return this;
    }

    public boolean isEnable() {
        return enable;
    }

    @Override
    public Log2FileConfig configLog2FilePath(String logPath) {
        this.logPath = logPath;
        return this;
    }

    /**
     * 获取日志路径
     * @return
     */
    public String getLogPath() {
        if (TextUtils.isEmpty(logPath)) {
            return getDefaultPath();
        }
        File file = new File(logPath);
        if (file.exists() && file.isDirectory()) {
            return logPath;
        }
        if(file.isFile() && file.getParentFile() != null) {
            if (file.getParentFile().exists()) {
                return file.getParent();
            } else {
                boolean ret = file.getParentFile().mkdirs();
                if (ret) {
                    return file.getParent();
                }
            }
        }
        boolean ret = file.mkdirs();
        if (ret) {
            return logPath;
        }
        return null;
    }

    @Override
    public Log2FileConfig configLog2FileNameFormat(String formatName) {
        if (!TextUtils.isEmpty(formatName)) {
            this.logFormatName = formatName;
        }
        return this;
    }

    public String getLogFormatName() {
        return new LogPattern.Log2FileNamePattern(logFormatName).doApply();
    }

    @Override
    public Log2FileConfig configLog2FileLevel(@LogLevel.LogLevelType int level) {
        this.logLevel = level;
        return this;
    }

    public int getLogLevel() {
        return logLevel;
    }

    @Override
    public Log2FileConfig configLogFileEngine(LogFileEngine engine) {
        this.engine = engine;
        return this;
    }

    @Override
    public Log2FileConfig configLogFileFilter(LogFileFilter fileFilter) {
        this.fileFilter = fileFilter;
        return this;
    }

    @Override
    public File getLogFile() {
        String path = getLogPath();
        if (!TextUtils.isEmpty(path)) {
            return new File(path, getLogFormatName());
        }
        return null;
    }

    public LogFileFilter getFileFilter() {
        return fileFilter;
    }

    public LogFileEngine getEngine() {
        return engine;
    }

    /**
     * 默认路径
     */
    public String getDefaultPath() {
        String basePath = "";
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            basePath = Environment.getExternalStorageDirectory() + File.separator;
        } else {
            throw new IllegalStateException("Sdcard No Access, please config Log2FilePath");
        }
        return basePath + Constant.TAG + File.separator + "logs";
    }
}
