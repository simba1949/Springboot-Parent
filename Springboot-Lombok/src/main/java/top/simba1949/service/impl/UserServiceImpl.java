package top.simba1949.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.simba1949.common.UserDto;
import top.simba1949.service.UserService;

/**
 * @author simba1949@outlook.com
 * @date 2018/7/11 13:18
 */
@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDto getUser(){
        UserDto userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setUsername("李白");
        userDto.setPassword("君不见黄河之水天上来");
        logger.info(userDto.toString());

        return userDto;
    }
}
