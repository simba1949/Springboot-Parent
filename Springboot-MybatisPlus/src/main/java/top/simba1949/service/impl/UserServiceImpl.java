package top.simba1949.service.impl;

import top.simba1949.common.User;
import top.simba1949.mapper.UserMapper;
import top.simba1949.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author simba@onlying.cn
 * @since 2018-07-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
