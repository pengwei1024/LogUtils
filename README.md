# LogUtils

![](https://img.shields.io/badge/Logutil-1.7.5-blue.svg)
![Codecov](https://img.shields.io/badge/log2File-1.3.1-brightgreen.svg)

#### More convenient and easy to use android Log manager

## Features
* 支持直接打印数据集合, 如List、Set、Map、数组等
* 全局配置log输出, 个性化设置Tag
* 准确显示调用方法、行，快速定位日志所在文件位置
* 支持android系统复杂对象Intent、Bundle、Message等打印
* 提供空实现 release-no-op版本
* 支持高性能日志写入文件(基于mmap)
* 兼容Android Studio 3.1 日志格式


## Usage

```groovy
implementation 'com.apkfuns.logutils:library:1.7.5'
```

#### release-no-op版本  (debug输出日志, release空实现)
```groovy
debugImplementation 'com.apkfuns.logutils:library:1.7.5'
releaseImplementation 'com.apkfuns.logutils:logutils-no-op:1.7.5'
```

#### 日志写入到文件 (基于[Log4a](https://github.com/pqpo/Log4a)实现）

```java
implementation 'com.apkfuns.log2file:log2file:1.3.1'


// 设置日志写文件引擎
LogUtils.getLog2FileConfig().configLogFileEngine(new LogFileEngineFactory(context));
```

别忘了添加写文件权限

```
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```

## Wiki
[使用教程](./README_USAGE.md)


## License
<pre>
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
