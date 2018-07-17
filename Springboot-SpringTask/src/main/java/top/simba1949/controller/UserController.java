package top.simba1949.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author v_jiayytian@tencent.com
 * @date 2018/7/9 20:29
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/string")
    public String string(){
        return "君不见黄河之水天上来";
    }

}
