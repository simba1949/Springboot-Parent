package top.simba1949.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import top.simba1949.event.DemoEvent;

/**
 * @author simba@onlying.cn
 * @date 2018/7/14 21:12
 * 自定义事件监听器，实现 ApplicationListener<事件>
 */
@Component
public class DemoListener implements ApplicationListener<DemoEvent> {

    @Override
    public void onApplicationEvent(DemoEvent demoEvent) {
        String msg = demoEvent.getMsg();
        System.err.println("DemoListener 接收到的消息是：" + msg);
    }
}
