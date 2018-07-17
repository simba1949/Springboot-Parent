# Springboot 集成 Logback 日志管理

> Springboot 默认日志管理系统是Logback日志管理，无需引如相关jar

## 网址

Logback官网：https://logback.qos.ch/

logback-classic maven地址：http://mvnrepository.com/artifact/ch.qos.logback/logback-classic

## pom依赖

```xml
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.3</version>
</dependency>
<!--
引入以上依赖，会自动引入以下jar
	logback-classic.x.x.x.jar
	logback-core.x.x.x.jar
	slf4j-api-x.x.x.jar
-->
```

## logback的配置介绍：

Logger、appender及layout 

```
- Logger作为日志的记录器，把它关联到应用的对应的context上后，主要用于存放日志对象，也可以定义日志类型、级别。 

- Appender主要用于指定日志输出的目的地，目的地可以是控制台、文件、远程套接字服务器、 MySQL、PostreSQL、 Oracle和其他数据库、 JMS和远程UNIX Syslog守护进程等。 

- Layout 负责把事件转换成字符串，格式化的日志信息的输出。 　
```

logger context 

```
各个logger 都被关联到一个 LoggerContext，LoggerContext负责制造logger，也负责以树结构排列各logger。其他所有logger也通过org.slf4j.LoggerFactory 类的静态方法getLogger取得。 getLogger方法以 logger名称为参数。用同一名字调用LoggerFactory.getLogger () 方法所得到的永远都是同一个logger对象的引用。 
```

有效级别及级别的继承 

```
Logger 可以被分配级别。级别包括：TRACE、DEBUG、INFO、WARN 和 ERROR，定义于ch.qos.logback.classic.Level类。如果 logger没有被分配级别，那么它将从有被分配级别的最近的祖先那里继承级别。root logger 默认级别是 DEBUG。 　
```

打印方法与基本的选择规则 

```
打印方法决定记录请求的级别。例如，如果 L 是一个 logger 实例，那么，语句 L.info("..")是一条级别为 INFO的记录语句。记录请求的级别在高于或等于其 logger 的有效级别时被称为被启用，否则，称为被禁用。记录请求级别为 p，其 logger的有效级别为 q，只有则当 p>=q时，该请求才会被执行。 该规则是 logback 的核心。级别排序为： TRACE < DEBUG < INFO < WARN < ERROR 
```

## logback常用配置

## logback.xml格式详解

### configuration

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--
	scan  属性设置为true时，配置文件如果发生改变，将会被重新加载，默认值为true。
	scanPeriod: 设置监测配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒。当scan为true时，此属性生效。默认的时间间隔为1分钟。
	debug: 当此属性设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。默认值为false。
	packagingData：Logback可以包括它输出的堆栈跟踪行的每一行的打包数据。打包数据由JAR文件的名称和版本组成，堆栈跟踪行的类就是在JAR文件中创建的。默认为false
-->
<configuration scan="true" scanPeriod="60 seconds" debug="false" packagingData="true">
	<!--其他配置略-->
</configuration>
```

#### contextName

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true" scanPeriod="60 seconds" debug="false">
    <!--
		用来设置上下文名称，每个logger都关联到logger上下文，默认上下文名称为default。但可以使用		<contextName>设置成其他名字，用于区分不同应用程序的记录。一旦设置，不能修改。
	-->
    <contextName>myApplicationName</contextName>
    
	<!--其他配置略-->
</configuration>
```

#### property

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true" scanPeriod="60 seconds" debug="false">
    <!--用来定义变量值，它有两个属性name和value，通过<property>定义的值会被插入到logger上下文中，可以		使“${}”来使用变量。
　　　　name: 变量的名称
　　　　value: 的值时变量定义的值
	-->
    <property name="keyName" value="myApplicationName" /> 
    <contextName>${keyName}</contextName>
    
	<!--其他配置略-->
</configuration>
```

#### timestamp

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true" scanPeriod="60 seconds" debug="false">
    
	<!--获取时间戳字符串，他有两个属性key和datePattern
　　　　key: 标识此<timestamp> 的名字；
　　　　datePattern: 设置将当前时间（解析配置文件的时间）转换为字符串的模式，遵循							java.txt.SimpleDateFormat的格式。
	-->
    <timestamp key="keyValue" datePattern="yyyy-MM-dd" />
    
    <contextName>${keyValue}</contextName>
    
	<!--其他配置略-->
</configuration>
```

#### appender

##### ConsoleAppender

###### 属性说明

| Property Name | Type                                                         | Description                                                  |
| ------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **encoder**   | [`Encoder`](https://logback.qos.ch/xref/ch/qos/logback/core/encoder/Encoder.html) | See `OutputStreamAppender` properties.                       |
| **target**    | `String`                                                     | One of the String values *System.out* or *System.err*. The default target is *System.out*. |
| **withJansi** | `boolean`                                                    | By the default withJansi property is set to `false`. Setting withJansi to `true` activates the [Jansi](http://jansi.fusesource.org/) library which provides support for ANSI color codes on Windows machines. On a Windows host, if this property is set to true, then you should put "org.fusesource.jansi:jansi:1.9" on the class path. Note that Unix-based operating systems such as Linux and Mac OS X support ANSI color codes by default.Under the Eclipse IDE, you might want to try the [ANSI in Eclipse Console](http://www.mihai-nita.net/eclipse/) plugin. |

###### 实例演示

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true" scanPeriod="60 seconds" debug="false">							<contextName>myApplicationName</contextName>
    <!--
		子节点<appender>：负责写日志的组件，它有两个必要属性name和class。
		name指定appender名称;
		class指定appender的全限定名;
	-->
    <appender name="console" class="ch.qos.logback.core.ConsoleAppender"> 
        <!--
			<encoder>：对日志进行格式化。
			<target>：字符串System.out(默认)或者System.err
			<withJansi>：
		-->
        <encoder> 
            <pattern>%-4relative [%thread] %-5level %logger{35} - %msg %n</pattern> 
        </encoder> 
    </appender>
    
    <root level="DEBUG">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```

##### FileAppender

###### 属性说明

| Property Name | Type                                                         | Description                                                  |
| ------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **append**    | `boolean`                                                    | If true, events are appended at the end of an existing file. Otherwise, if append is false, any existing file is truncated. The append option is set to true by default. |
| **encoder**   | [`Encoder`](https://logback.qos.ch/xref/ch/qos/logback/core/encoder/Encoder.html) | See `OutputStreamAppender` properties.                       |
| **file**      | `String`                                                     | The name of the file to write to. If the file does not exist, it is created. On the MS Windows platform users frequently forget to escape back slashes. For example, the value *c:\temp\test.log* is not likely to be interpreted properly as *'\t'* is an escape sequence interpreted as a single tab character *(\u0009)*. Correct values can be specified as *c:/temp/test.log* or alternatively as *c:\\temp\\test.log*. The File option has no default value.If the parent directory of the file does not exist, `FileAppender` will automatically create it, including any necessary but nonexistent parent directories. |
| **prudent**   | `boolean`                                                    | In prudent mode, `FileAppender` will safely write to the specified file, even in the presence of other `FileAppender` instances running in different JVMs, potentially running on different hosts. The default value for prudent mode is `false`.Prudent mode can be used in conjunction with `RollingFileAppender` although some [restrictions apply](https://logback.qos.ch/manual/appenders.html#prudentWithRolling).Prudent mode implies that append property is automatically set to true.Prudent more relies on exclusive file locks. Experiments show that file locks approximately triple (x3) the cost of writing a logging event. On an "average" PC writing to a file located on a **local** hard disk, when prudent mode is off, it takes about 10 microseconds to write a single logging event. When prudent mode is on, it takes approximately 30 microseconds to output a single logging event. This translates to logging throughput of 100'000 events per second when prudent mode is off and approximately 33'000 events per second in prudent mode.Prudent mode effectively serializes I/O operations between all JVMs writing to the same file. Thus, as the number of JVMs competing to access a file increases so will the delay incurred by each I/O operation. As long as the *total* number of I/O operations is in the order of 20 log requests per second, the impact on performance should be negligible. Applications generating 100 or more I/O operations per second can see an impact on performance and should avoid using prudent mode.NETWORKED FILE LOCKS When the log file is located on a networked file system, the cost of prudent mode is even greater. Just as importantly, file locks over a networked file system can be sometimes strongly biased such that the process currently owning the lock immediately re-obtains the lock upon its release. Thus, while one process hogs the lock for the log file, other processes starve waiting for the lock to the point of appearing deadlocked.The impact of prudent mode is highly dependent on network speed as well as the OS implementation details. We provide an very small application called [FileLockSimulator](https://gist.github.com/2794241) which can help you simulate the behavior of prudent mode in your environment. |

###### 实例演示

```XML
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

  <appender name="FILE" class="ch.qos.logback.core.FileAppender">
    <!--
		<file>：被写入的文件名，可以使相对目录，也可以是绝对目录
		<append>：如果是 true，日志被追加到文件结尾，如果是 false，清空现存文件，默认是true。
		<prudent>：如果是 true，日志会被安全的写入文件，即使其他的FileAppender也在向此文件做写	入操作，效率低，默认是 false。
		<immediateFlush>：将immediateFlush设置为false以获得更高的日志吞吐量
	-->
    <file>testFile.log</file>
    <append>true</append>
    <!-- set immediateFlush to false for much higher logging throughput -->
    <immediateFlush>true</immediateFlush>
    <!-- encoders are assigned the type
         ch.qos.logback.classic.encoder.PatternLayoutEncoder by default -->
    <encoder>
      <pattern>%-4relative [%thread] %-5level %logger{35} - %msg%n</pattern>
    </encoder>
  </appender>
        
  <root level="DEBUG">
    <appender-ref ref="FILE" />
  </root>
</configuration>
```

##### RollingFileAppender

###### 属性说明

| Property Name        | Type                                                         | Description                                                  |
| -------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **file**             | `String`                                                     | See `FileAppender` properties.                               |
| **append**           | `boolean`                                                    | See `FileAppender` properties.                               |
| **encoder**          | [`Encoder`](https://logback.qos.ch/xref/ch/qos/logback/core/encoder/Encoder.html) | See `OutputStreamAppender` properties.                       |
| **rollingPolicy**    | `RollingPolicy`                                              | This option is the component that will dictate `RollingFileAppender`'s behavior when rollover occurs. See more information below. |
| **triggeringPolicy** | `TriggeringPolicy`                                           | This option is the component that will tell `RollingFileAppender` when to activate the rollover procedure. See more information below. |
| **prudent**          | `boolean`                                                    | [`FixedWindowRollingPolicy`](https://logback.qos.ch/manual/appenders.html#FixedWindowRollingPolicy) is not supported in prudent mode.`RollingFileAppender` supports the prudent mode in conjunction with [`TimeBasedRollingPolicy`](https://logback.qos.ch/manual/appenders.html#TimeBasedRollingPolicy) albeit with two restrictions.In prudent mode, file compression is not supported nor allowed. (We can't have one JVM writing to a file while another JVM is compressing it.)The file property of `FileAppender` cannot be set and must be left blank. Indeed, most operating systems do not allow renaming of a file while another process has it opened.See also properties for `FileAppender`. |

###### 

###### 实例演示——TimeBasedRollingPolicy

* Here is a sample configuration for `RollingFileAppender` in conjunction with a `TimeBasedRollingPolicy`. 

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <configuration>
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
      <file>logFile.log</file>
      <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <!-- daily rollover -->
        <fileNamePattern>logFile.%d{yyyy-MM-dd}.log</fileNamePattern>
  
        <!-- keep 30 days' worth of history capped at 3GB total size -->
        <maxHistory>30</maxHistory>
        <totalSizeCap>3GB</totalSizeCap>
  
      </rollingPolicy>
  
      <encoder>
        <pattern>%-4relative [%thread] %-5level %logger{35} - %msg%n</pattern>
      </encoder>
    </appender> 
  
    <root level="DEBUG">
      <appender-ref ref="FILE" />
    </root>
  </configuration>
  ```

* The next configuration sample illustrates the use of `RollingFileAppender` associated with `TimeBasedRollingPolicy` in prudent mode. 

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <configuration>
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
      <!-- Support multiple-JVM writing to the same log file -->
      <prudent>true</prudent>
      <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <fileNamePattern>logFile.%d{yyyy-MM-dd}.log</fileNamePattern>
        <maxHistory>30</maxHistory> 
        <totalSizeCap>3GB</totalSizeCap>
      </rollingPolicy>
  
      <encoder>
        <pattern>%-4relative [%thread] %-5level %logger{35} - %msg%n</pattern>
      </encoder>
    </appender> 
  
    <root level="DEBUG">
      <appender-ref ref="FILE" />
    </root>
  </configuration>
  ```

###### 实例演示——SizeAndTimeBasedRollingPolicy

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
  <appender name="ROLLING" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>mylog.txt</file>
    <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
      <!-- rollover daily -->
      <fileNamePattern>mylog-%d{yyyy-MM-dd}.%i.txt</fileNamePattern>
       <!-- each file should be at most 100MB, keep 60 days worth of history, but at most 20GB -->
       <maxFileSize>100MB</maxFileSize>    
       <maxHistory>60</maxHistory>
       <totalSizeCap>20GB</totalSizeCap>
    </rollingPolicy>
    <encoder>
      <pattern>%msg%n</pattern>
    </encoder>
  </appender>

  <root level="DEBUG">
    <appender-ref ref="ROLLING" />
  </root>

</configuration>
```

###### 实例演示——FixedWindowRollingPolicy

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
  <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>test.log</file>

    <rollingPolicy class="ch.qos.logback.core.rolling.FixedWindowRollingPolicy">
      <fileNamePattern>tests.%i.log.zip</fileNamePattern>
      <minIndex>1</minIndex>
      <maxIndex>3</maxIndex>
    </rollingPolicy>

    <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
      <maxFileSize>5MB</maxFileSize>
    </triggeringPolicy>
    <encoder>
      <pattern>%-4relative [%thread] %-5level %logger{35} - %msg%n</pattern>
    </encoder>
  </appender>
        
  <root level="DEBUG">
    <appender-ref ref="FILE" />
  </root>
</configuration>
```

##### DBAppender

###### logging_event

The *logging_event* table contains the following fields: 

| Field                 | Type       | Description                                                  |
| --------------------- | ---------- | ------------------------------------------------------------ |
| **timestamp**         | `big int`  | The timestamp that was valid at the logging event's creation. |
| **formatted_message** | `text`     | The message that has been added to the logging event, after formatting with `org.slf4j.impl.MessageFormatter`, in case objects were passed along with the message. |
| **logger_name**       | `varchar`  | The name of the logger used to issue the logging request.    |
| **level_string**      | `varchar`  | The level of the logging event.                              |
| **reference_flag**    | `smallint` | This field is used by logback to identify logging events that have an exception or `MDC`property values associated.Its value is computed by `ch.qos.logback.classic.db.DBHelper`. A logging event that contains `MDC` or `Context` properties has a flag number of *1*. One that contains an exception has a flag number of *2*. A logging event that contains both elements has a flag number of *3*. |
| **caller_filename**   | `varchar`  | The name of the file where the logging request was issued.   |
| **caller_class**      | `varchar`  | The class where the logging request was issued.              |
| **caller_method**     | `varchar`  | The name of the method where the logging request was issued. |
| **caller_line**       | `char`     | The line number where the logging request was issued.        |
| **event_id**          | `int`      | The database id of the logging event.                        |

###### *logging_event_property* 

| Field            | Type      | Description                           |
| ---------------- | --------- | ------------------------------------- |
| **event_id**     | `int`     | The database id of the logging event. |
| **mapped_key**   | `varchar` | The key of the `MDC` property         |
| **mapped_value** | `text`    | The value of the `MDC` property       |

######   *logging_event_exception* 

| Field          | Type       | Description                                    |
| -------------- | ---------- | ---------------------------------------------- |
| **event_id**   | `int`      | The database id of the logging event.          |
| **i**          | `smallint` | The index of the line in the full stack trace. |
| **trace_line** | `varchar`  | The corresponding line                         |

###### 实例演示

无连接池配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

  <appender name="DB" class="ch.qos.logback.classic.db.DBAppender">
        <connectionSource class="ch.qos.logback.core.db.DataSourceConnectionSource">
            <driverClassName>com.mysql.jdbc.Driver</driverClassName>
            <url>jdbc:mysql://localhost:3306/springboot</url>
            <username>root</username>
            <password>19491001</password>
        </connectionSource>
    </appender>
  
  <root level="DEBUG" >
    <appender-ref ref="DB" />
  </root>
</configuration>
```

连接池配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <appender name="DB" class="ch.qos.logback.classic.db.DBAppender">
        <connectionSource class="ch.qos.logback.core.db.DataSourceConnectionSource">
            <dataSource class="com.alibaba.druid.pool.DruidDataSource">
                <driverClassName>com.mysql.jdbc.Driver</driverClassName>
                <url>jdbc:mysql://localhost:3306/springboot</url>
                <username>root</username>
                <password>19491001</password>
            </dataSource>
        </connectionSource>
    </appender>

  <root level="DEBUG">
    <appender-ref ref="DB" />
  </root>
</configuration>
```

##### 其他Appender详细配置网址

https://logback.qos.ch/manual/appenders.html

#### encoders 语法

```
#logback.classic pattern: %d [%thread] %-5level %logger{36} - %msg%n
2012-04-26 14:54:38,461 [main] DEBUG com.foo.App - Hello world
2012-04-26 14:54:38,461 [main] DEBUG com.foo.App - Hi again
...
```

## 附录

### logback.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<configuration scan="true" scanPeriod="60 seconds" debug="false">
    <contextName>myApplicationName</contextName>
    <timestamp key="dateFile" datePattern="yyyy-MM-dd" />
    <property name="LOG_HOME" value="Springboot-Logback/logs" />
    <!-- 控制台设置 -->
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <!-- encoder 默认配置为PatternLayoutEncoder -->
        <encoder>
            <pattern>%d [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    <!--输出到文件中-->
    <appender name="FILE" class="ch.qos.logback.core.FileAppender">
        <file>${LOG_HOME}/log-${dateFile}.log</file>
        <append>true</append>
        <immediateFlush>true</immediateFlush>
        <encoder>
            <pattern>%d [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <appender name="SPRING-APPENDER" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${LOG_HOME}/springframework.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <FileNamePattern>${LOG_HOME}/springframework.%d{yyyy-MM-dd}.log.gz</FileNamePattern>
            <maxHistory>30</maxHistory>
            <totalSizeCap>20GB</totalSizeCap>
        </rollingPolicy>
        <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
            <layout class="ch.qos.logback.classic.PatternLayout">
                <pattern>%d [%thread] %-5level %logger{36} %line - %msg%n</pattern>
            </layout>
        </encoder>
    </appender>

    <appender name="DB" class="ch.qos.logback.classic.db.DBAppender">
        <connectionSource class="ch.qos.logback.core.db.DataSourceConnectionSource">
            <dataSource class="com.alibaba.druid.pool.DruidDataSource">
                <driverClassName>com.mysql.jdbc.Driver</driverClassName>
                <url>jdbc:mysql://localhost:3306/springboot</url>
                <username>root</username>
                <password>19491001</password>
            </dataSource>
        </connectionSource>
    </appender>


    <logger name="top.simba1949.controller" level="info">
        <appender-ref ref="FILE" />
    </logger>

    <logger name="top.simba1949" level="error">
        <appender-ref ref="DB" />
    </logger>


    <!-- 通配符 设置log打印级别 对所有类有效TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF-->
    <root level="DEBUG">
        <appender-ref ref="STDOUT" />
        <appender-ref ref="SPRING-APPENDER" />
    </root>
</configuration>
```

### logback在mysql数据库建表语句

```sql
# Logback: the reliable, generic, fast and flexible logging framework.
# Copyright (C) 1999-2010, QOS.ch. All rights reserved.
#
# See http://logback.qos.ch/license.html for the applicable licensing 
# conditions.

# This SQL script creates the required tables by ch.qos.logback.classic.db.DBAppender.
#
# It is intended for MySQL databases. It has been tested on MySQL 5.1.37 
# on Linux


BEGIN;
DROP TABLE IF EXISTS logging_event_property;
DROP TABLE IF EXISTS logging_event_exception;
DROP TABLE IF EXISTS logging_event;
COMMIT;


BEGIN;
CREATE TABLE logging_event 
  (
    timestmp         BIGINT NOT NULL,
    formatted_message  TEXT NOT NULL,
    logger_name       VARCHAR(254) NOT NULL,
    level_string      VARCHAR(254) NOT NULL,
    thread_name       VARCHAR(254),
    reference_flag    SMALLINT,
    arg0              VARCHAR(254),
    arg1              VARCHAR(254),
    arg2              VARCHAR(254),
    arg3              VARCHAR(254),
    caller_filename   VARCHAR(254) NOT NULL,
    caller_class      VARCHAR(254) NOT NULL,
    caller_method     VARCHAR(254) NOT NULL,
    caller_line       CHAR(4) NOT NULL,
    event_id          BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
  );
COMMIT;

BEGIN;
CREATE TABLE logging_event_property
  (
    event_id	      BIGINT NOT NULL,
    mapped_key        VARCHAR(254) NOT NULL,
    mapped_value      TEXT,
    PRIMARY KEY(event_id, mapped_key),
    FOREIGN KEY (event_id) REFERENCES logging_event(event_id)
  );
COMMIT;

BEGIN;
CREATE TABLE logging_event_exception
  (
    event_id         BIGINT NOT NULL,
    i                SMALLINT NOT NULL,
    trace_line       VARCHAR(254) NOT NULL,
    PRIMARY KEY(event_id, i),
    FOREIGN KEY (event_id) REFERENCES logging_event(event_id)
  );
COMMIT;
```

