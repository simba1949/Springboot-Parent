package top.simba1949.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author simba@onlying.cn
 * @date 2018/7/15 14:55
 */
@Component
public class TestProperties {

    @Value("${user.username}")
    String username;
    @Value("${user.age}")
    int age;
    @Value("${user.flag}")
    boolean flag;

    @Override
    public String toString() {
        System.out.println("username" + username);
        System.out.println("age" + age);
        System.out.println("flag" + flag);
        return "TestProperties{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", flag=" + flag +
                '}';
    }

}
