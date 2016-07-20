package com.apkfuns.logutils.demo.model;

/**
 * Created by pengwei on 16/3/22.
 */
public class Child<T> {
    private T parent;
    private String name;

    public Child(String name) {
        this.name = name;
    }

    public T getParent() {
        return parent;
    }

    public void setParent(T parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
