# Springboot集成SpringTask

## 基于注解配置

### 第一步

在springboot启动类中添加注解@EnableScheduling 

```java
package top.simba1949;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author simba@onlying.cn
 * @date 2018/7/9 20:08
 */
@SpringBootApplication
@EnableScheduling // 支持schedule定时任务
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
```

### 第二步

编写定时任务

```java
package top.simba1949.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author simba@onlying.cn
 * @date 2018/7/10 20:52
 */
@Component
public class MySpringTask {

    private Logger logger = LoggerFactory.getLogger(MySpringTask.class);
    
    /**
     * spring task，@Scheduled(cron = "0/5 * * * * ? ")即可开启定时任务
     */
    @Scheduled(cron = "0/5 * * * * ? ")
    public void doTask(){
        logger.info(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + "  "+ Thread.currentThread().getName());
    }
}
```

## 基于配置文件

### 第一步

创建applicationContext-task.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:task="http://www.springframework.org/schema/task"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
        http://www.springframework.org/schema/task http://www.springframework.org/schema/task/spring-task.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 扫描组件 -->
    <context:component-scan base-package="top.simba1949.task" />
    <!-- 配置调度器,声明十个线程的池 -->
    <task:scheduler id="myScheduler" pool-size="10"/>
    <!-- 配置任务 -->
    <task:scheduled-tasks scheduler="myScheduler">
        <!--ref引用的定时任务的Bean，method指定该bean要执行的方法-->
        <task:scheduled ref="mySpringTask" method="doTask" cron="0/5 * * * * ? "/>
        <!--配置定时任务2-->
        <task:scheduled ref="mySpringTask02" method="doTask" cron="0/3 * * * * ? "/>
    </task:scheduled-tasks>

</beans>
```

### 第二步

将配置文件加入spring容器管理

```java
package top.simba1949.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author simba@onlying.cn
 * @date 2018/7/9 20:09
 */
@Configuration
@ImportResource(locations = "applicationContext-springtask.xml")
public class SpringTaskConfig {

}
```

### 第三步

编写定时任务

```java
package top.simba1949.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadFactory;

/**
 * @author simba@onlying.cn
 * @date 2018/7/10 20:52
 */
@Component
public class MySpringTask {

    private Logger logger = LoggerFactory.getLogger(MySpringTask.class);

    /**
     * spring task，@Scheduled(cron = "0/5 * * * * ? ")即可开启定时任务
     */
    public void doTask(){
        logger.info(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + "  "+ Thread.currentThread().getName());
    }
}
```





