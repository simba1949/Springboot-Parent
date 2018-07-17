package top.simba1949.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author simba@onlying.cn
 * @date 2018/7/14 21:12
 * 自定义事件，继承 ApplicationEvent
 */
public class DemoEvent extends ApplicationEvent {

    private String msg;

    public DemoEvent(Object source) {
        super(source);
    }

    public DemoEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
