package top.simba1949.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author simba@onlying.cn
 * @date 2018/7/15 16:02
 * @ConfigurationProperties(prefix = "book") 指定前缀
 * @PropertySource("classpath:properties/book.properties") 指定文件路径
 */
@Component
@ConfigurationProperties(prefix = "book")
@PropertySource("classpath:properties/book.properties")
public class BookProperties {

    private String name;
    private int age;
    private boolean flag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "BookProperties{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", flag=" + flag +
                '}';
    }
}
