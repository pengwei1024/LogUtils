package com.apkfuns.logutils.demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.apkfuns.logutils.demo.helper.DataHelper;
import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.LogUtils;
import com.apkfuns.logutils.demo.R;
import com.apkfuns.logutils.parser.BundleParse;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogUtils.getLogConfig()
                .configAllowLog(true)
                .configTagPrefix("duLife-")
                .configShowBorders(true)
                .configLevel(LogLevel.TYPE_VERBOSE)
                .addParserClass(BundleParse.class)
        ;

        LogUtils.d("12345");
        LogUtils.d("12%s3%s45", "a", "b");
        LogUtils.d(new NullPointerException("12345"));
        LogUtils.d(DataHelper.getObject());
        LogUtils.d(null);

        LogUtils.json(DataHelper.getJson());
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

        // Bundle支持
        Bundle bundle = new Bundle();
        bundle.putInt("abc", 1);
        bundle.putString("abc2", "text");
        bundle.putSerializable("abc3", DataHelper.getObject());
        bundle.putStringArray("abc4", DataHelper.getStringArray());
        bundle.putSerializable("abc7", DataHelper.getStringArray2());
        bundle.putSerializable("abc8", DataHelper.getStringArray3());
        bundle.putSerializable("abc5", DataHelper.getObject());
        bundle.putSerializable("abc6", DataHelper.getObjectArray());
        bundle.putSerializable("abc9", DataHelper.getStringMap());
        bundle.putSerializable("abc10", DataHelper.getBigString(this));
        LogUtils.e(bundle);


        LogUtils.e(DataHelper.getMan());
        LogUtils.e(DataHelper.getObject());
        LogUtils.e(DataHelper.getOldMan());

        // 大文本输出
        LogUtils.e(DataHelper.getBigString(this));

        Intent it = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        LogUtils.e(it);

    }
}
