# LogUtils
More convenient and easy to use android Log manager

### Features
>* ``支持直接打印数据集合``,如List、Set、Map、数组等
>* ``全局配置log输出``
>* ``不需要设置tag``
>* ``准确显示调用方法、行，快速定位所在文件位置``.

### screenshot
##### 效果图
![截图](screenshot/screenshot01.png)
##### 日志说明
![截图](screenshot/screenshot02.png)
##### 打印数据列表
![截图](screenshot/screenshot03.png)
##### 打印数组
![截图](screenshot/screenshot04.png)

## Simple
```java

// 输出字符串
LogUtils.d("12345");

// 输出参数
LogUtils.d("12%s3%d45", "a", 0);

// 输出异常
LogUtils.d(new NullPointerException("12345"));

// 输出对象
Person person = new Person();
person.setAge(11);
person.setName("pengwei");
person.setScore(37.5f);
LogUtils.d(person);

// 对象为空
LogUtils.d(null);

// 输出json（json默认debug打印）
String json = "{'a':'b','c':{'aa':234,'dd':{'az':12}}}";
LogUtils.json(json);

// 打印数据集合
List<Person> list1 = new ArrayList<>();
for(int i = 0; i < 4; i++){
    list1.add(person);
}
LogUtils.d(list1);

// 打印数组
double[][] doubles = {{1.2, 1.6, 1.7, 30, 33},
                {1.2, 1.6, 1.7, 30, 33},
                {1.2, 1.6, 1.7, 30, 33},
                {1.2, 1.6, 1.7, 30, 33}};
LogUtils.d(doubles);

// 其他用法
LogUtils.v("12345");
LogUtils.i("12345");
LogUtils.w("12345");
LogUtils.e("12345");
LogUtils.wtf("12345");
```

### options
```java
// 配置日志是否输出(默认true)
LogUtils.configAllowLog = false;

// 配置日志前缀
LogUtils.configTagPrefix = "abc-";
```
## Usage

### Gradle
```groovy
compile 'com.apkfuns.logutils:library:1.0.5'
```
### Maven
```xml
<dependency>
    <groupId>com.apkfuns.logutils</groupId>
    <artifactId>library</artifactId>
    <version>1.0.5</version>
</dependency>
```

### Jar
click [here](http://jcenter.bintray.com/com/apkfuns/logutils/library/) to download sources.jar

## History
* **1.0.0 (2015/07/13)**
    - 打印对象，字符串，异常，且显示文件行数
* **1.0.1 (2015/07/22)**
    - 打印json字符串
* **1.0.2 (2015/07/24)**
    - 支持打印List、Se等数据集合
* **1.0.3 (2015/07/24)**
    - 支持打印Map集合
* **1.0.4 (2015/07/25)**
    - 支持打印数组(暂仅支持一维、二维数组)
* **1.0.5 (2015/08/11)**
        - 修复打印字符串包含%s崩溃的bug



## About
* Blog: [apkfuns.com](http://apkfuns.com?from=github)
* Email: [pengwei1024@gmail.com](http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=pengwei1024@gmail.com)

### Thanks
thanks to [wyouflf/xUtils](https://github.com/wyouflf/xUtils) 和[orhanobut/logger](https://github.com/orhanobut/logger)

### License
<pre>
Copyright 2015 Orhan Obut

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</pre>
