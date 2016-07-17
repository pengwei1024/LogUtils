package com.apkfuns.logutils.laboratory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.noveogroup.android.log.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestActivity extends AppCompatActivity {

    private static final Logger SLF_LOGGER = LoggerFactory.getLogger(TestActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        SLF_LOGGER.info("test info  : {}", "message 100%!");

        TextView tv = (TextView) findViewById(R.id.tv_test);
        tv.setOnClickListener(v -> LogUtils.d("test lambda!"));


        LogUtils.tag("tagtagtagttagtagtagttagtagtagttagtagtagt+tagtagtagttagtagtagttagtagtagttagtagtagt").e("12312313");
    }

    public void onSampleSimple(View view) {
        Log.i("sample message");
        Log.e("sample error message");
    }

    public void onSampleDownload(View view) {
        Downloader.download();
    }
}
