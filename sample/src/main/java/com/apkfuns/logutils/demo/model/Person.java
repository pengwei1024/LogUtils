package com.apkfuns.logutils.demo.model;

import java.io.Serializable;

/**
 * Created by pengwei08 on 2015/7/20.
 */
public class Person implements Serializable {
    private String name;
    private int age;
    private float score;
    private Person other;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

}
