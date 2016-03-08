package com.apkfuns.logutils.demo.helper;

import com.apkfuns.logutils.demo.model.Person;

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

}
