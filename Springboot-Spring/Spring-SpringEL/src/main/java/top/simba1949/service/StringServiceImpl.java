package top.simba1949.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author simba@onlying.cn
 * @date 2018/7/14 20:02
 */
@Service
public class StringServiceImpl {
    /**
     * 这里注入一个普通的字符串
     */
    @Value("StringServiceImpl")
    private String serviceName;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
