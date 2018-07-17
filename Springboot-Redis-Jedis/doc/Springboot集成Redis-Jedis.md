# Springboot集成Redis-Jedis

## 网址

jedis maven地址：https://mvnrepository.com/artifact/redis.clients/jedis

## pom依赖

在 pom.xml 添加依赖

```xml
<!-- jedis start -->
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <type>jar</type>
    <scope>compile</scope>
</dependency>
<!-- jedis   end -->
```

## 项目

### pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>top.simba1949</groupId>
    <artifactId>Springboot-Redis-Jedis</artifactId>
    <version>1.0-SNAPSHOT</version>

    <!-- 继承spring-boot-start-parent -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.3.RELEASE</version>
    </parent>

    <!--配置管理-->
    <properties>
        <!--配置项目编码-->
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <!--jdk编译版本-->
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>

    <!--依赖管理-->
    <dependencies>
        <!--springboot-web start-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--springboot-web  end-->

        <!-- jedis start -->
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <type>jar</type>
            <scope>compile</scope>
        </dependency>
        <!-- jedis   end -->

        <!--springboot test start-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!--springboot test   end-->
    </dependencies>
</project>
```

### 配置文件

application.yml

```yaml
server:
  port: 8888
```

### 代码

启动类 App.class

```java
package top.simba1949;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author simba@onlying.cn
 * @date 2018/7/11 18:18
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
```

简单的Jedis控制层，SimpleJedisController.class

```java
package top.simba1949.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 * @author simba@onlying.cn
 * @date 2018/7/11 18:19
 */
@RequestMapping("/jedis")
@RestController
public class SimpleJedisController {

    private Logger logger = LoggerFactory.getLogger(SimpleJedisController.class);

    /**
     * 简单redis使用
     * @param key
     * @param value
     * @return
     */
    @PostMapping
    public String setKey(String key,String value){
        // 获取 Redis 客户端，连接服务器
        Jedis jedis = new Jedis("127.0.0.1",6379);
        // 使用 redis 语法
        jedis.set(key,value);
        logger.info(key + value);
        // 释放资源
        jedis.close();
        return key + value;
    }

    @GetMapping
    public String getKey(String key){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        return jedis.get(key);
    }
}
```

使用连接池Jedis的控制层，JedisPoolController.class

```java
package top.simba1949.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author simba@onlying.cn
 * @date 2018/7/11 19:18
 */
@RequestMapping("/pool")
@RestController
public class JedisPoolController {

    private Logger logger = LoggerFactory.getLogger(JedisPoolController.class);

    @PostMapping
    public String setKeyByJedispool(String key,String value){
        // 获取连接池配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 设置最大连接数
        jedisPoolConfig.setMaxTotal(20);
        // 设置最大空闲连接数
        jedisPoolConfig.setMaxIdle(10);

        // 创建连接池
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);

        Jedis jedis = null;
        try {
            // 从连接池获取 Jedis 资源
            jedis = jedisPool.getResource();
            jedis.set(key,value);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
            if (jedisPool != null) {
                jedisPool.close();
            }
        }
        logger.info(key + value);
        return key + value;
    }

    @GetMapping
    public String getKeyByJedispool(String key){
        // 获取连接池配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 设置最大连接数
        jedisPoolConfig.setMaxTotal(20);
        // 设置最大空闲连接数
        jedisPoolConfig.setMaxIdle(10);

        // 创建连接池
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);

        Jedis jedis = null;
        String value = null;
        try {
            // 从连接池获取 Jedis 资源
            jedis = jedisPool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
            if (jedisPool != null) {
                jedisPool.close();
            }
        }
        logger.info(value);
        return value;
    }
}
```

