package top.simba1949.config;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * @author simba@onlying.cn
 * @date 2018/7/14 20:01
 */
@Configuration
@ComponentScan("top.simba1949")
@PropertySource("classpath:config/jdbc.properties")
public class ELConfig {
    /**
     * 注入普通的字符串
     */
    @Value("注入的是普通的字符串")
    private String string;
    /**
     * 注入操作系统的属性
     */
    @Value("#{ systemProperties['os.name']}")
    private String onName;
    /**
     * 注入运算表达式的结果
     */
    @Value("#{ T(java.lang.Math).random() * 1000}")
    private double randomNumber;
    /**
     * 注入其他Bean的属性
     */
    @Value("#{stringServiceImpl.serviceName}")
    private String otherBeanProperty;
    /**
     * 注入文件内容
     */
    @Value("classpath:config/jdbc.properties")
    private Resource fileResource;
    /**
     * 注入网址内容
     */
    @Value("http://www.baidu.com")
    private Resource urlResource;
    /**
     * 注入文件中属性
     */
    @Value("${jdbc.driverClassName}")
    private String jdbcDriver;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUsername;
    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Autowired
    private Environment environment;

   @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    public void outputProperty(){
        System.out.println("注入的是普通的字符串" + string);
        System.out.println("注入的是操作系统的属性" + onName);
        System.out.println("注入的是表达式运算的结果" + randomNumber);
        System.out.println("注入的是其他Bean的属性" + otherBeanProperty);
        try {
            System.out.println(IOUtils.toString(fileResource.getInputStream(),"utf-8"));
            System.out.println(IOUtils.toString(urlResource.getInputStream(),"utf-8"));
            System.out.println(jdbcDriver);
            System.out.println(jdbcUrl);
            System.out.println(jdbcUsername);
            System.out.println(jdbcPassword);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("===============================我是可爱的分割线==========================================");
        System.out.println(environment.getProperty("jdbc.driverClassName"));
    }
}
