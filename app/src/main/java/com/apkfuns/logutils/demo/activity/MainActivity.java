package com.apkfuns.logutils.demo.activity;

import android.app.Activity;
import android.os.Bundle;

import com.apkfuns.logutils.demo.helper.DataHelper;
import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.LogUtils;
import com.apkfuns.logutils.demo.R;



public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogUtils.getLogConfig()
                .configAllowLog(true)
                .configTagPrefix("duLife-")
                .configShowBorders(true)
                .configLevel(LogLevel.TYPE_VERBOSE);

        LogUtils.d("12345");
        LogUtils.d("12%s3%s45", "a", "b");
        LogUtils.d(new NullPointerException("12345"));
        LogUtils.d(DataHelper.getObject());
        LogUtils.d(null);


//
//        LogUtils.json(DataHelper.getJson());
//
//        // 打印List
        LogUtils.d(DataHelper.getStringList());
//
//        // 支持数据集合
        LogUtils.d(DataHelper.getObjectList());
//
//        // 支持map输出
        LogUtils.d(DataHelper.getObjectMap());
//


        Bundle bundle = new Bundle();
        bundle.putInt("abc", 1);
        bundle.putString("abc2", "text");
        bundle.putSerializable("abc3", DataHelper.getObject());
        bundle.putStringArray("abc4", DataHelper.getStringArray());
        bundle.putSerializable("abc7", DataHelper.getStringArray2());
        bundle.putSerializable("abc8", DataHelper.getStringArray3());
        bundle.putSerializable("abc5", DataHelper.getObject());
        bundle.putSerializable("abc6", DataHelper.getObjectArray());
        LogUtils.e(bundle);
        LogUtils.e(bundle.toString());
    }
}
