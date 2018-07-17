package top.simba1949;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.simba1949.config.ELConfig;

/**
 * @author simba@onlying.cn
 * @date 2018/7/14 20:20
 */
public class TestSpringEL {

    @Test
    public void output(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ELConfig.class);
        ELConfig bean = context.getBean(ELConfig.class);
        bean.outputProperty();
        context.close();
    }
}
