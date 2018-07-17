package top.simba1949.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author v_jiayytian@tencent.com
 * @date 2018/7/10 21:24
 */
@Component
public class MySpringTask02 {

    private Logger logger = LoggerFactory.getLogger(MySpringTask.class);

    /**
     * spring task，@Scheduled(cron = "0/5 * * * * ? ")即可开启定时任务
     */
    @Scheduled(cron = "0/3 * * * * ? ")
    public void doTask(){
        logger.info(new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "  "+ Thread.currentThread().getName());
    }
}
