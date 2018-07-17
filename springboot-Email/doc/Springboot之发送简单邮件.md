# Springboot之发送简单邮件

## 添加依赖

在 pom.xml 添加依赖

```xml
<!-- springboot 发送邮件 start -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
<!-- springboot 发送邮件 end -->
```
## 邮件配置

在 application.yml 中配置

```yaml
spring:
  #springboot发送邮件的配置
  mail:
      host: smtp.qq.com（邮箱服务器地址）
      username: 邮箱地址
      password: 邮箱密码
      properties:
          mail:
              smtp:
                  auth: true
                  timeout: 25000
```

## 代码

```java
package top.simba1949.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author simba@onlying.cn
 * @date 2018/7/6 20:00
 */
@Service
public class EmailServiceImpl {
	/**
     * 获取配置文件中邮箱地址
     */
    @Value("${spring.mail.username}")
    private String emailFrom;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleMail(String sendTo, String title, String content) {
        // 创建邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 发送邮件地址
        message.setFrom(emailFrom);
        // 接收邮件地址
        message.setTo(sendTo);
        // 邮件主题
        message.setSubject(title);
        // 邮件内容
        message.setText(content);

        // 发送邮件
        javaMailSender.send(message);
    }
}
```