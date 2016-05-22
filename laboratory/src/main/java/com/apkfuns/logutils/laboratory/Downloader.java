package com.apkfuns.logutils.laboratory;

import com.noveogroup.android.log.Log;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.io.IOException;

public class Downloader {

    private Downloader() {
        throw new UnsupportedOperationException();
    }

    private static final Logger LOGGER = LoggerManager.getLogger();

    public static void download() {
        LOGGER.v("entering [Downloader::download]");
        new Thread() {
            @Override
            public void run() {
                LOGGER.i("start downloading ...");
                try {
                    Thread.sleep(1000);
                    Log.e("... downloading ...");
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
                LOGGER.e("downloader can not download", new IOException("no downloading code"));
            }
        }.start();
        LOGGER.v("exiting [Downloader::download]");
    }

}