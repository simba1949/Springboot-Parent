package top.simba1949.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author v_jiayytian@tencent.com
 * @date 2018/7/10 21:57
 */
@Component
public class EmailConfig {

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleMail(String sendTo, String title, String content){
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
