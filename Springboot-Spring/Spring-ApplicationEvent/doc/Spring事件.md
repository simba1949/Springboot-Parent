## Spring事件

## 前言

Spring的事件（Application Event）为 Bean 与 Bean 之间的消息通信提供了支持。当一个 Bean 处理完一个任务之后，希望另外一个 Bean 知道并作出相应的处理，这时我们就需要让另外一个 Bean 监听当前 Bean 所发送的事件。

Spring 事件需要遵循的流程：

1. 自定义事件，继承 ApplicationEvent
2. 定义事件监听器，实现 ApplicationListener
3. 使用容器发布事件

## 项目

### 自定义事件

DemoEvent.java

```java
package top.simba1949.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author simba@onlying.cn
 * @date 2018/7/14 21:12
 * 自定义事件，继承 ApplicationEvent
 */
public class DemoEvent extends ApplicationEvent {

    private String msg;

    public DemoEvent(Object source) {
        super(source);
    }

    public DemoEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
```

### 自定义事件监听器

DemoListener.java

```java
package top.simba1949.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import top.simba1949.event.DemoEvent;

/**
 * @author simba@onlying.cn
 * @date 2018/7/14 21:12
 * 自定义事件监听器，实现 ApplicationListener<事件> 
 */
@Component
public class DemoListener implements ApplicationListener<DemoEvent> {

    @Override
    public void onApplicationEvent(DemoEvent demoEvent) {
        String msg = demoEvent.getMsg();
        System.err.println("DemoListener 接收到的消息是：" + msg);
    }
}
```

### 配置文件类

EventConfig.java

```java
package top.simba1949.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author simba@onlying.cn
 * @date 2018/7/14 21:19
 * 用于扫描spring的bean
 */
@Configuration
@ComponentScan("top.simba1949")
public class EventConfig {
}
```

### 发布事件

DemoPublisher.java

```java
package top.simba1949.publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import top.simba1949.event.DemoEvent;

/**
 * @author simba@onlying.cn
 * @date 2018/7/14 21:16
 * 发布事件
 */
@Component
public class DemoPublisher {

    @Autowired
    ApplicationContext applicationContext;

    public void publish(String msg){
        applicationContext.publishEvent(new DemoEvent(this,msg));
    }
}
```

