package com.apkfuns.logutils.demo.helper;

import android.content.Context;

import com.apkfuns.logutils.demo.model.Man;
import com.apkfuns.logutils.demo.model.Node;
import com.apkfuns.logutils.demo.model.OldMan;
import com.apkfuns.logutils.demo.model.Person;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pengwei on 16/3/8.
 */
public final class DataHelper {

    /**
     * 获取对象
     *
     * @return
     */
    public static Person getObject() {
        Person person = new Person();
        person.setAge(11);
        person.setName("pengwei");
        person.setScore(37.5f);
        return person;
    }

    /**
     * 对象List
     *
     * @return
     */
    public static List<Person> getObjectList() {
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(getObject());
        }
        return list;
    }

    public static List<String> getStringList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    public static Map<String, Person> getObjectMap() {
        Map<String, Person> map = new HashMap<>();
        map.put("a", getObject());
        map.put("b", getObject());
        map.put("c", getObject());
        return map;
    }

    public static HashMap<String, String> getStringMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");
        return map;
    }

    public static int[] getIntArray() {
        int[] intArray = {1, 2, 3, 4, 5, 6};
        return intArray;
    }

    public static Integer[] getIntegerArray() {
        Integer[] intArray2 = {1, 2, 3, 4, 5, 6};
        return intArray2;
    }

    public static Person[] getObjectArray() {
        Person[] persons = {getObject(), getObject(), getObject(), getObject()};
        return persons;
    }

    public static Person[][] getObjectArray2() {
        Person[][] persons = {{getObject(), getObject(), getObject(), getObject()},
                {getObject(), getObject(), getObject(), getObject()}};
        return persons;
    }

    public static String[][][] getStringArray3() {
        String[][][] stringArray3 = {{{"1", "2"}, {"1", "2"}, {"1", "2"}, {"1", "2"}, {"1", "2"}},
                {{"1", "2"}, {"1", "2"}, {"1", "2"}, {"1", "2"}, {"1", "2"}}};
        return stringArray3;
    }

    public static String[][] getStringArray2() {
        String[][] stringArray2 = {{"1", "2"}, {"1", "2"}, {"1", "2"}, {"1", "2"}, {"1", "2"}};
        return stringArray2;
    }

    public static String[] getStringArray() {
        String[] stringArray = {"1", "2", "1", "2", "1", "2", "1", "2", "1", "2"};
        return stringArray;
    }

    /**
     * 获取json对象
     *
     * @return
     */

    public static String getJson() {
        String json = "{'a':'b','c':{'aa':234,'dd':{'az':12}}}";
        return json;
    }

    public static Man getMan() {
        Man person = new Man(1);
        person.setAge(12);
        person.setName("pengwei+1");
        person.setScore(80.5f);
        return person;
    }

    public static Man getOldMan() {
        Man person = new OldMan(1);
        person.setAge(13);
        person.setName("pengwei+2");
        person.setScore(81.5f);
        return person;
    }

    /**
     * 大文本
     *
     * @param context context
     * @return file content
     */
    public static String getBigString(Context context) {
        try {
            InputStream is = context.getAssets().open("city.json");
            StringBuilder sb = new StringBuilder();
            int len;
            byte[] buf = new byte[is.available()];
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len, "utf-8"));
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取Node
     * 避免对象包含自己死循环问题
     *
     * @param leftNode
     * @param rightNode
     * @return
     */
    public static Node getNode(String leftNode, String rightNode) {
        Node node = new Node("parent Node");
        Node left1 = new Node("left1");
        node.setLeft(left1);
        Node left2 = new Node("left2");
        left1.setLeft(left2);
        Node left3 = new Node("left3");
        left2.setLeft(left3);
        node.setRight(new Node(rightNode));
        return node;
    }

    public static String getXml() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Pensons><Penson id=\"1\"><name>name</name><sex>男</sex><age>30</age></Penson><Penson id=\"2\"><name>name</name><sex>女</sex><age>27</age></Penson></Pensons>";
    }

}
