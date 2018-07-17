package top.simba1949.properties;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author simba@onlying.cn
 * @date 2018/7/15 15:34
 */
@Component
@PropertySource("classpath:properties/test.properties")
public class TestPropertiess {

    @Value("${user.username}")
    private String username;

    @Value("${user.age}")
    private int age;
    @Value("${user.flag}")
    private boolean flag;
    /**
     * 可以获取文件的流的读写
     */
    @Value("classpath:properties/test.properties")
    private Resource resource;
    /**
     * environment 代表 test.properties 文件，
     * 可以通过 environment.getProperty("user.username") 获取属性值
     */
    @Autowired
    private Environment environment;

    public String output() throws IOException {
        System.out.println(username);
        System.out.println(age);
        System.out.println(flag);
        System.out.println(IOUtils.toString(resource.getInputStream(),"utf-8"));
        System.out.println("environment" + environment.getProperty("user.username"));

        return "username: " + username + "  age: " + age + "  flag: " + flag + "  environment: " + environment.getProperty("user.username")
                + "  resource: "  + IOUtils.toString(resource.getInputStream(),"utf-8");
    }
}
