package top.simba1949.controller;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.simba1949.common.UserDto;
import top.simba1949.service.UserService;

/**
 * @author v_jiayytian@tencent.com
 * @date 2018/7/11 12:44
 */
@RequestMapping("/user")
@RestController
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    public UserDto getDto(){
        logger.info("userController: getDto");
        return userService.getUser();
    }

    @PutMapping
    public UserDto userDto(){
        logger.info("userController: userDto");
        return userService.getUser();
    }
}
