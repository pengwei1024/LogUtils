# LogUtils
<!--*[查看中文版](./doc/README_CN.md)*<br/>-->
More convenient and easy to use android Log manager

## 1. Features
* 支持直接打印数据集合,如List、Set、Map、数组等
* 全局配置log输出
* 不需要设置tag
* 准确显示调用方法、行，快速定位所在文件位置
* 支持android系统对象Intent、Bundle打印

## 2. screenshot
##### 日志说明
![截图](screenshot/screenshot02.png)
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

##### Demo
```java
LogUtils.getLogConfig()
                .configAllowLog(true)
                .configTagPrefix("MyAppName")
                .configShowBorders(true)
                .configLevel(LogLevel.TYPE_VERBOSE)
```

##### 自定义对象打印
实现Parser<T>接口，并实现parseClassType() 和parseString()方法,再通过addParserClass()配置到LogUtil
<a href='./doc/custom_parser.md'>详细文档</a>


## 4. Usage

### Gradle导入
```groovy
compile 'com.apkfuns.logutils:library:1.2.2'
```
### Maven导入
```xml
<dependency>
    <groupId>com.apkfuns.logutils</groupId>
    <artifactId>library</artifactId>
    <version>1.2.2</version>
</dependency>
```

### Eclipse导入Jar
click [here](https://github.com/pengwei1024/LogUtils/tree/master/annex) to download *.jar

## 5. Skills
* [从Log切换到LogUtils](./doc/log_to_logutils.md)
想使用`LogUtils`但是项目中已经广泛使用`Log.*(TAG, String)`改动太大? 

* [为LogUtils设置快捷键](./doc/logutils_templates.md)
输入`psvm`就可以快速输出public static void main(String[] args) {} 
输入`logd`就可以快速输出Log.d(TAG, "main: ");<br/>

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

## 7. About
* Blog: [apkfuns.com](http://apkfuns.com?from=github)
* Email: [pengwei1024@gmail.com](http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=pengwei1024@gmail.com)

## 8. Thanks
thanks to [wyouflf/xUtils](https://github.com/wyouflf/xUtils)、[orhanobut/logger](https://github.com/orhanobut/logger)、
[ZhaoKaiQiang/KLog](https://github.com/ZhaoKaiQiang/KLog)

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
