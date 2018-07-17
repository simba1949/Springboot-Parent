# Springboot 学习笔记之配置 devtools 实现热部署

## springboot热部署在IDEA中的配置

一、File——>Setting——>Compiler——>勾选Build project automatically

二、按下 Ctrl + Shift + A，搜索 registry——>勾选 compiler.automake.allow.when.app.running

## devtools 原理

> 深层原理是使用了两个ClassLoader，一个Classloader加载那些不会改变的类（第三方Jar包），另一个ClassLoader加载会更改的类，称为restart ClassLoader,这样在有代码更改的时候，原来的restart ClassLoader 被丢弃，重新创建一个restart ClassLoader，由于需要加载的类相比较少，所以实现了较快的重启时间。 

## 配置 devtools

在pom.xml 添加 spring-boot-devtools 的依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional> <!-- 表示依赖不会传递 -->
</dependency>
```

在 pom.xml 配置插件

```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <fork>true</fork> <!-- 如果没有该配置，devtools不会生效 -->
    </configuration>
</plugin>
```