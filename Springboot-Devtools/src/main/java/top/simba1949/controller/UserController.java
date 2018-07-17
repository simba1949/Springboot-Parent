package top.simba1949.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author v_jiayytian@tencent.com
 * @date 2018/7/10 21:44
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/string")
    public String string(){
        String str = "君不见黄河之水天上来";
        String string = "6666";
        logger.info(str);
        return str + "" + string;
    }
}
