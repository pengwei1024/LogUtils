package com.apkfuns.logutils.demo.model;

import com.apkfuns.logutils.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by pengwei on 16/5/19.
 */
public class FakeBounty {
    public List<A> mA = new ArrayList<>();

    public class A {
        int x = new Random().nextInt();
        int $change = 2;
//        int this$0 = 4;
        String $abc = "aaa";

        public A() {
            LogUtils.e("A");
        }
    }

    public FakeBounty() {
        for (int i = 0; i < 20; i++) {
            mA.add(new A());
        }
    }
}
