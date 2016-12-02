# LogUtils
<!--*[查看中文版](./doc/README_CN.md)*<br/>-->
More convenient and easy to use android Log manager

## 1. Features
* 支持直接打印数据集合,如List、Set、Map、数组等
* 全局配置log输出
* 个性化设置Tag
* 准确显示调用方法、行，快速定位所在文件位置
* 支持android系统对象Intent、Bundle打印
* 提供release-no-op版本

## 2. screenshot
##### 日志说明
![截图](screenshot/screenshot02.png)
##### 个性化设置Tag
![截图](screenshot/screenshot08.png)
##### 打印数据列表
![截图](screenshot/screenshot03.png)
##### 打印数组
![截图](screenshot/screenshot04.png)
##### 打印对象本身属性和继承的属性
![截图](screenshot/screenshot05.png)
##### 打印系统对象Intent
![截图](screenshot/screenshot06.png)

## 3. Simple
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

// 自定义tag
LogUtils.tag("我是自定义tag").d("我是打印内容");

// 其他用法
LogUtils.v("12345");
LogUtils.i("12345");
LogUtils.w("12345");
LogUtils.e("12345");
LogUtils.wtf("12345");
```

### options
方法 | 描述 | 取值 | 缺省 
------- | ------- | ------- | -------
configAllowLog | 是否允许日志输出 | boolean | true 
configTagPrefix | 日志log的前缀 | String | "LogUtils"
configShowBorders | 是否显示边界 | boolean | false
configLevel | 日志显示等级 | LogLevelType | LogLevel.TYPE_VERBOSE
addParserClass | 自定义对象打印 | Parser | 无 
configFormatTag | 个性化设置Tag | String | %c{-5}

##### Demo
```java
LogUtils.getLogConfig()
                .configAllowLog(true)
                .configTagPrefix("MyAppName")
                .configShowBorders(true)
                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}")
                .configLevel(LogLevel.TYPE_VERBOSE)
```

##### configFormatTag参数详解
变量 | 简写 | 描述  | 参数 | 示例 | 输出结果
------- | ------- | -------   | -------  | ------- | ------- 
%% | 无 | 转义% | 无|%%d|%d
%date | %d | 当前时间  | 格式化时间,如HH:mm:ss|%d{HH:mm:ss:SSS}|10:00:46:238
%thread | %t | 当前线程名称 | 无|%t|thread-127
%caller | %c | 线程信息和类路径  |一般用%c{-5}就好了，用法为%c{整数}或者%caller{整数}，整数为包名路径，如路径为`com.apkfuns.logutils.demo.MainActivity.onCreate(MainActivity.java:135)`,%c{1}输出`com`，以.分割的第一个,如果小于0就是排除前面n个，如%c{-1} ，结果为`apkfuns.logutils.demo.activity.MainActivity.onCreate(MainActivity.java:135)`,|%c{-5}|MainActivity.onCreate(MainActivity.java:135)

##### 自定义对象打印
实现Parser<T>接口，并实现parseClassType() 和parseString()方法,再通过addParserClass()配置到LogUtil
<a href='./doc/custom_parser.md'>详细文档</a>


## 4. Usage

### Gradle
```groovy
compile 'com.apkfuns.logutils:library:1.4.2.2'
```

##### release-no-op版本
```groovy
debugCompile 'com.apkfuns.logutils:library:1.4.2.2'
releaseCompile 'com.apkfuns.logutils:logutils-no-op:1.4.2.1'
``` 

### Eclipse导入Jar
click [here](https://github.com/pengwei1024/LogUtils/tree/master/annex) to download *.jar

## 5. Skills
* [从Log切换到LogUtils](./doc/log_to_logutils.md)
* [为LogUtils设置快捷键](./doc/logutils_templates.md)
* [修改LogCat显示字体颜色](./doc/logcat_color.md)

## 6. History
* **1.0.0 (2015/07/13)**
    - 打印对象，字符串，异常
    - 显示报错文件名、行数
* **1.0.1 (2015/07/22)**
    - 打印json字符串
* **1.0.2 (2015/07/24)**
    - 支持打印List、Se等数据集合
* **1.0.3 (2015/07/24)**
    - 支持打印Map集合
* **1.0.4 (2015/07/25)**
    - 支持打印数组(暂仅支持一维、二维数组)
* **1.0.6 (2015/08/28)**
    - 修复打印字符串包含%s崩溃的bug
* **1.1.0 (2016/03/02)**
    - 修复非Exception崩溃的错误
* **1.2.0 (2016/03/09)**
    - 支持android系统对象Bundle、Intent等打印
    - 优化设置选项
    - 支持多维数组
    - 支持自定义对象打印
    - 支持字符串超过4k打印
* **1.2.1 (2016/03/14)**
    - 支持Intent具体Flags显示
    - 修复自定义Parse和自带Parse优先级问题
* **1.2.2 (2016/03/22)**
    - 修复打印Reference类的bug
* **1.3.0 (2016/04/20)**
    - 支持对象包含复杂对象，逻辑重构
* **1.3.1 (2016/05/09)**
    - 修复某些情况下出现死循环的情况
* **1.4.0 (2016/05/19)**
    - 支持设置临时tag
    - 支持xml打印  
    - 解决[issue 10](https://github.com/pengwei1024/LogUtils/issues/10)内部类问题
* **1.4.2 (2016/05/23)**
    - 个性化设置Tag(configFormatTag();)
* **1.4.3 (开发中)**    
    - 修复Instant Run下非静态内部类死循环情况
    

## 7. About
* Blog: [apkfuns.com](http://apkfuns.com?from=github)
* Email: [pengwei1024@gmail.com](http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=pengwei1024@gmail.com)

## 8. Thanks
* thanks to [tinybright](https://github.com/tinybright)、[DrSlark](https://github.com/DrSlark)'s advice.
* thanks to [wyouflf/xUtils](https://github.com/wyouflf/xUtils)、[orhanobut/logger](https://github.com/orhanobut/logger)、
[ZhaoKaiQiang/KLog](https://github.com/ZhaoKaiQiang/KLog)、[noveogroup/android-logger](https://github.com/noveogroup/android-logger)

## 9. License
<pre>
Copyright 2015-2016 pengwei1024

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
