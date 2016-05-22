package com.apkfuns.logutils.demo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengwei on 16/5/19.
 */
public class FakeBounty {
    public List<A> mA = new ArrayList<>();

    public class A {
        protected int a = 5;
        public A() {

        }
    }

    public FakeBounty() {
        for (int i = 0; i < 20; i++) {
            mA.add(new A());
        }
    }
}
