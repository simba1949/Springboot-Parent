package top.simba1949;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.simba1949.config.EventConfig;
import top.simba1949.publish.DemoPublisher;

/**
 * @author simba@onlying.cn
 * @date 2018/7/14 21:20
 */
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
        DemoPublisher bean = context.getBean(DemoPublisher.class);
        bean.publish("hello application event");
        context.close();
    }
}
