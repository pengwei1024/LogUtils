package com.apkfuns.logutils.laboratory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.noveogroup.android.log.Log;
import com.noveogroup.android.log.LoggerManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainActivity extends AppCompatActivity {

    private static final Logger SLF_LOGGER = LoggerFactory.getLogger(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SLF_LOGGER.info("test info  : {}", "message 100%!");
    }

    public void onSampleSimple(View view) {
        Log.i("sample message");
        Log.e("sample error message");
    }

    public void onSampleDownload(View view) {
        Downloader.download();
    }
}
