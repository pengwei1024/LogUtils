package com.apkfuns.logutils.demo.model;

/**
 * Created by pengwei on 16/4/18.
 */
public class Fruit {
    private String name;
    private boolean canEat;

    public Fruit(String name, boolean canEat) {
        this.name = name;
        this.canEat = canEat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCanEat() {
        return canEat;
    }

    public void setCanEat(boolean canEat) {
        this.canEat = canEat;
    }
}
