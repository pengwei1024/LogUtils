# LogUtils
More convenient and easy to use android Log manager

### screenshot
![截图](screenshot/screenshot01.png)

### Simple
```java
StackTraceElement st[]= Thread.currentThread().getStackTrace();
for(int i=0;i<st.length;i++)
   Log.wtf("abc",i + ":" + st[i]);
Log.wtf("abc",st[2].toString());
String a = st[2].toString();
Log.wtf("abc",a.substring(a.lastIndexOf('('), a.length()));
```

### options
```java
// 配置日志是否输出(默认true)
LogUtils.configAllowLog = false;

// 配置日志统一Tag
LogUtils.configTagPrefix = "tag";
```

### Gradle
```groovy
compile '***'
```

### History

* **1.0.0 (2015/07/13)**
    - 新增abc
    - 新增edf


## About
* Blog: [apkfuns.com](http://apkfuns.com)
* Email: [pengwei1024@gmail.com](http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=pengwei1024@gmail.com)

### Thanks
感谢[wyouflf/xUtils](https://github.com/wyouflf/xUtils) 和[orhanobut/logger](https://github.com/orhanobut/logger)

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
