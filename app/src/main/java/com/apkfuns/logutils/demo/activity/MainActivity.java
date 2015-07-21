package com.apkfuns.logutils.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.apkfuns.logutils.LogUtils;
import com.apkfuns.logutils.demo.R;
import com.apkfuns.logutils.demo.model.Person;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Person person = new Person();
        person.setAge(11);
        person.setName("pengwei");
        person.setScore(37.5f);

        LogUtils.configTagPrefix = "abc-";
        LogUtils.configAllowLog = true;

        LogUtils.d("12345");
        LogUtils.d("12%s3%s45", "a","b");
        LogUtils.d(new NullPointerException("12345"));
        LogUtils.d(person);
        LogUtils.d(null);

        LogUtils.v("12345");
        LogUtils.i("12345");
        LogUtils.w("12345");
        LogUtils.e("12345");
        LogUtils.wtf("12345");

        String json = "{'a':'b','c':{'aa':234,'dd':{'az':12}}}";
        LogUtils.json(json);

    }
}
