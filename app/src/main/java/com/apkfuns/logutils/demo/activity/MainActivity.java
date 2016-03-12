package com.apkfuns.logutils.demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.apkfuns.logutils.demo.helper.DataHelper;
import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.LogUtils;
import com.apkfuns.logutils.demo.R;
import com.apkfuns.logutils.demo.parse.CollectParse;
import com.apkfuns.logutils.parser.BundleParse;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


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
                .addParserClass(OkHttpResponseParse.class, CollectParse.class);

//        LogUtils.d("12345");
//        LogUtils.d("12%s3%s45", "a", "b");
//        LogUtils.d(new NullPointerException("12345"));
//        LogUtils.d(DataHelper.getObject());
//        LogUtils.d(null);

//        LogUtils.json(DataHelper.getJson());
//
//        // 打印List
        LogUtils.d(DataHelper.getStringList());
//
//        // 支持数据集合
//        LogUtils.d(DataHelper.getObjectList());

//        // 支持map输出
//        LogUtils.d(DataHelper.getObjectMap());

        // Bundle支持
//        Bundle bundle = new Bundle();
//        bundle.putInt("abc", 1);
//        bundle.putString("abc2", "text");
//        bundle.putSerializable("abc3", DataHelper.getObject());
//        bundle.putStringArray("abc4", DataHelper.getStringArray());
//        bundle.putSerializable("abc7", DataHelper.getStringArray2());
//        bundle.putSerializable("abc8", DataHelper.getStringArray3());
//        bundle.putSerializable("abc5", DataHelper.getObject());
//        bundle.putSerializable("abc6", DataHelper.getObjectArray());
//        bundle.putSerializable("abc9", DataHelper.getStringMap());
//        bundle.putSerializable("abc10", DataHelper.getBigString(this));
//        LogUtils.e(bundle);


        // 对象测试
//        LogUtils.e(DataHelper.getMan());
//        LogUtils.e(DataHelper.getObject());
//        LogUtils.e(DataHelper.getOldMan());

        // 大文本输出
//        LogUtils.e(DataHelper.getBigString(this));

        // Intent测试
//        Intent it = new Intent(
//                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        it.putExtra("aaaa", "12345");
//        it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
//        LogUtils.d(it);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://www.baidu.com").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.e(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogUtils.e(response);
            }
        });
    }
}
