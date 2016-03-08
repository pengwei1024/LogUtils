package com.apkfuns.logutils.demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.apkfuns.logutils.parser.BundleParse;
import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.LogUtils;
import com.apkfuns.logutils.demo.R;
import com.apkfuns.logutils.demo.model.Person;
import com.apkfuns.logutils.parser.CollectionParse;
import com.apkfuns.logutils.parser.IntentParse;
import com.apkfuns.logutils.parser.MapParse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Person person = new Person();
        person.setAge(11);
        person.setName("pengwei");
        person.setScore(37.5f);

        LogUtils.getLogConfig()
                .configAllowLog(true)
                .configTagPrefix("duLife-")
                .configShowBorders(true)
                .configLevel(LogLevel.TYPE_VERBOSE);

//        LogUtils.configTagPrefix = "abc-";
//        LogUtils.configAllowLog = true;


//        LogUtils.d("12345");
//        LogUtils.d("12%s3%s45", "a", "b");
//        LogUtils.d(new NullPointerException("12345"));
//        LogUtils.d(person);
//        LogUtils.d(null);
//
//        LogUtils.v("12345");
//        LogUtils.i("12345");
//        LogUtils.w("12345");
//        LogUtils.e("12345");
//        LogUtils.wtf("12345");
//
//        String json = "{'a':'b','c':{'aa':234,'dd':{'az':12}}}";
//        LogUtils.json(json);
//
//        // 打印List
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            list.add(String.valueOf(i));
//        }
//        LogUtils.d(list);
//
//        // 支持数据集合
        List<Person> list1 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list1.add(person);
        }
        LogUtils.d(list1);
//
//        // 支持map输出
        Map<String, Person> map = new HashMap<>();
        map.put("a", person);
        map.put("b", person);
        map.put("c", person);
        LogUtils.d(map);
//
//        // 打印数组
        String[] stringArray = {"1", "2", "3", "4", "5", "6"};
//        LogUtils.d(stringArray);
        String[][] stringArray2 = {{"1", "2"}, {"1", "2"}, {"1", "2"}, {"1", "2"}, {"1", "2"}};
//        LogUtils.d(stringArray2);
//        int[][] ints2 = {};
//        LogUtils.d(ints2);
//        double[][] doubles = {{1.2, 1.6, 1.7, 30, 33},
//                {1.2, 1.6, 1.7, 30, 33},
//                {1.2, 1.6, 1.7, 30, 33},
//                {1.2, 1.6, 1.7, 30, 33}};
//        LogUtils.d(doubles);
        int[] intArray = {1, 2, 3, 4, 5, 6};
        Integer[] intArray2 = {1, 2, 3, 4, 5, 6};
//        LogUtils.d(intArray);
//
//        // 打印对象数组
        Person[] persons = {person, person, person, person};
//        LogUtils.d(persons);
        Person[][] persons1 = {{person, person, person}, {person, person}};
//        LogUtils.d(persons1);
//
//        String[] s = new String[]{"a","b"};
//        LogUtils.d(s.toString());
//        String[][] s1 = new String[][]{};
//        LogUtils.d(s1.toString());
//        int[][] s2 = new int[][]{};
//        LogUtils.d(s2.toString());
//        LogUtils.d(ArrayParseUtil.getType(s));
//
//        // 保护%字符串
//        LogUtils.d("abcde%s");


        Bundle bundle = new Bundle();
        bundle.putInt("abc", 1);
        bundle.putString("abc2", "text");
        bundle.putSerializable("abc3", person);
        bundle.putStringArray("abc4", stringArray);
        bundle.putSerializable("abc5", persons);
        LogUtils.e(bundle);
        LogUtils.e(bundle.toString());

        Intent it = new Intent(this, MainActivity.class);
        it.putExtra("abc3", "abc");
        LogUtils.i(it);
        LogUtils.d(it.toString());

        LogUtils.e(String.valueOf(stringArray instanceof Object));
        LogUtils.e(String.valueOf(stringArray2 instanceof Object));

        LogUtils.e(new NullPointerException("****** NullPointerException()"));
        LogUtils.d(new NullPointerException("****** NullPointerException()"));

        Log.e("***", persons.getClass().getComponentType().toString());
        Log.e("***", persons1.getClass().getComponentType().toString());
        Log.e("***", stringArray.getClass().getComponentType().toString());
        Log.e("***", stringArray2.getClass().getComponentType().toString());
        Log.e("***", intArray.getClass().getComponentType().toString());
        Log.e("***", intArray2.getClass().getComponentType().toString());
    }
}
