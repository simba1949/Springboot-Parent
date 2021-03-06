package top.simba1949.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author simba1949@outlook.com
 * @date 2018/7/11 9:17
 */
@RequestMapping("/user")
@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);


    @GetMapping("/string")
    public String string(){
        String str = "君不见黄河之水天上来";
        logger.info(str);
        return str;
    }
}
