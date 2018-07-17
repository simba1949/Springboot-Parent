# Springboot 集成 MybatisPlus

## MP网址

MP 官网：http://mp.baomidou.com

MP maven地址：http://mvnrepository.com/artifact/com.baomidou/mybatis-plus

mybatis-plus-boot-starter maven地址：http://mvnrepository.com/artifact/com.baomidou/mybatis-plus-boot-starter

## MP依赖

在pom.xml添加依赖

```xml
<!-- springboot 集成 mp start-->
<dependency>
	<groupId>com.baomidou</groupId>
	<artifactId>mybatis-plus-boot-starter</artifactId>
	<version>2.3</version>
</dependency>
<!--如果要使用mp的代码生成还需要加入模板引擎,不建议使用maven插件生成啦！！！-->
<dependency>
	<groupId>org.apache.velocity</groupId>
	<artifactId>velocity-engine-core</artifactId>
	<version>2.0</version>
</dependency>
<!-- springboot 集成 mp  end-->
```

## MP配置

```yaml
server:
  port: 8888

# datasoure默认使用JDBC
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    username: root
    password: 19491001
    url: jdbc:mysql://127.0.0.1/test?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true

#mybaits-plus配置，修改主键类型，mapper.xml、type 别名等
mybatis-plus:
  mapper-locations: classpath:/top/simba1949/mapper/*Mapper.xml
  typeAliasesPackage: top.simba1949.common
  global-config:
    #主键类型  0:"数据库ID自增", 1:"用户输入ID",2:"全局唯一ID (数字类型唯一ID)", 3:"全局唯一ID UUID";
    id-type: 0
    #字段策略 0:"忽略判断",1:"非 NULL 判断"),2:"非空判断"
    field-strategy: 0
    #驼峰下划线转换
    db-column-underline: true
    #刷新mapper 调试神器
    refresh-mapper: true
    #数据库大写下划线转换
    #capital-mode: true
  configuration:
    map-underscore-to-camel-case: true
    cache-enabled: true
```

## 使用MP代码生成器

```java
package top.simba1949.config;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author simba@onlying.cn
 * @date 2018/7/6 16:02
 */
public class MPGenerator {
    /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("F://");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor("simba@onlying.cn");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("19491001");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        strategy.setTablePrefix(new String[] { "tlog_", "tsys_" });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("top.simba1949")
                .setController("controller")
                .setService("service")
                .setServiceImpl("service.impl")
                .setMapper("mapper")
                .setEntity("common").setXml("mapper");
        mpg.setPackageInfo(pc);

        // 执行生成
        mpg.execute();
    }
}
```

更多详细配置请见官网；

