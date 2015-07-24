package com.apkfuns.logutils.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.apkfuns.logutils.LogUtils;
import com.apkfuns.logutils.demo.R;
import com.apkfuns.logutils.demo.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

        List<String> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            list.add(String.valueOf(i));
        }
        LogUtils.d(list);

        // 支持数据集合
        List<Person> list1 = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            list1.add(person);
        }
        LogUtils.d(list1);

        // 支持map输出
        Map<String, Person> map = new HashMap<>();
        map.put("a", person);
        map.put("b", person);
        map.put("c", person);
        LogUtils.d(map);
    }
}
