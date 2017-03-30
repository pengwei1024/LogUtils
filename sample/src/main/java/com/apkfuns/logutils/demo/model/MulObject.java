package com.apkfuns.logutils.demo.model;


import com.apkfuns.logutils.demo.helper.DataHelper;

import java.util.List;

/**
 * Created by pengwei on 16/4/18.
 */
public class MulObject {
    private int part;
    private Man man;
    private Fruit fruit;
    private Person person = DataHelper.getObject();
    private List<Person> persons = DataHelper.getObjectList();

    public MulObject(int part) {
        this.part = part;
        this.man = new Man(2);
        this.fruit = new Fruit("苹果", true);
        fruit.setCanEat(false);
    }
}
