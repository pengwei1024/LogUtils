package com.apkfuns.logutils.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.apkfuns.logutils.demo.model.Person;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Person person = new Person();
        person.setAge(11);
        //person.setName("pengwei");
        person.setScore(37.5f);
//        LogUtils.configTagPrefix = "abc";
//        LogUtils.d(person);
//        LogUtils.d("my age is %d, name is %s", 12, "彭炜");
//        LogUtils.e("123456");
//        LogUtils.e("123456");
//        LogUtils.e("123456");
//        LogUtils.e("123456");
////
////        Logger.i("1234567%s", "abc");
////        Logger.e(new NullPointerException(), "1234567");
////        Log.wtf("abc", "123456", new NullPointerException());
//        Log.wtf("abc", "7891011");
//
//        print();

        Log.e("abc","123455");
//        Log.e("abc","123455", null);
//        Log.e("abc","123455", new NullPointerException("56789"));

    }

    private void print(){
        StackTraceElement st[]= Thread.currentThread().getStackTrace();
        for(int i=0;i<st.length;i++)
            Log.wtf("abc",i + ":" + st[i]);
        Log.wtf("abc",st[2].toString());
        String a = st[2].toString();
        Log.wtf("abc",a.substring(a.lastIndexOf('('), a.length()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
