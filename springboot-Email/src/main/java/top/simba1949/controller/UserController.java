package top.simba1949.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.simba1949.config.EmailConfig;

/**
 * @author simba1949@outlook.com
 * @date 2018/7/10 21:54
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private EmailConfig emailConfig;

    @GetMapping("/string")
    public String string(){
        emailConfig.sendSimpleMail("282323349@qq.com","测试邮件主题","测试邮件内容");
        return "君不见黄河之水天上来";
    }
}
