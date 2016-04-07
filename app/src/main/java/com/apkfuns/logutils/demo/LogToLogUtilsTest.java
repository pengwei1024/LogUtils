package com.apkfuns.logutils.demo;

import android.util.Log;

/**
 * Created by pengwei on 16/4/5.
 */
public class LogToLogUtilsTest {

    public static final String TAG = LogToLogUtilsTest.class.getName();
    public static final String value = "123";

    public void test() {
        Log.d("abc", "123");

        Log .d("abc", "123");

        Log. d("abc", "123");

        Log.wtf("abc", "123");

        Log.e (TAG, "123");

        Log.i(TAG, value);

        Log.i(TAG, value   );

        Log.e(TAG, "123", new Exception());

        Log.e(TAG, "123",
                new Exception());

        Log.e(TAG,

                "123",

                new Exception());

        Log.d("abc", String.valueOf(1));

        Log.wtf("abc", String.valueOf(1));

    }
}
