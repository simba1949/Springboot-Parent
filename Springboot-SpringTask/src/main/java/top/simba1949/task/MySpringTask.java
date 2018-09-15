package top.simba1949.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadFactory;

/**
 * @author simba1949@outlook.com
 * @date 2018/7/10 20:52
 */
@Component
public class MySpringTask {

    private Logger logger = LoggerFactory.getLogger(MySpringTask.class);

    /**
     * spring task，@Scheduled(cron = "0/5 * * * * ? ")即可开启定时任务
     */
    @Scheduled(cron = "0/5 * * * * ? ")
    public void doTask(){
        logger.info(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + "  "+ Thread.currentThread().getName());
    }
}
