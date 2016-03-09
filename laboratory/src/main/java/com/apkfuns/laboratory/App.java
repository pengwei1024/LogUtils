package com.apkfuns.laboratory;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by pengwei on 16/3/9.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init().logLevel(LogLevel.FULL);
    }
}
