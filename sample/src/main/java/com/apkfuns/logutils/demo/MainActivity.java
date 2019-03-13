package com.apkfuns.logutils.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.apkfuns.logutils.demo.helper.DataHelper;
import com.apkfuns.logutils.demo.service.MultiProcessService;
import com.apkfuns.logutils.demo.utils.ThreadLog;

import hugo.weaving.DebugLog;


public class MainActivity extends Activity implements View.OnClickListener {

    @DebugLog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewGroup container = findViewById(R.id.container);
        for (int i = 0; i < container.getChildCount(); i++) {
            container.getChildAt(i).setTag(i);
            container.getChildAt(i).setOnClickListener(this);
        }
        // 进行多进程测试
        startService(new Intent(this, MultiProcessService.class));
    }

    @Override
    public void onClick(View v) {
        switch (((int) v.getTag())) {
            case 0:
                LogUtils.d("12345!!");
                break;
            case 1:
                LogUtils.i(DataHelper.getBigString(this));
                break;
            case 2:
                new ThreadLog("thread1", "msg from 1").start();
                new ThreadLog("thread2", "msg from 2").start();
                new ThreadLog("thread3", "msg from 3").start();
                break;
            case 3:
                LogUtils.getLog2FileConfig().flushAsync();
                break;
            case 4:
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory("coding");
                intent.setAction("action");
                LogUtils.i(intent);
                break;
            default:
                break;
        }
    }
}
