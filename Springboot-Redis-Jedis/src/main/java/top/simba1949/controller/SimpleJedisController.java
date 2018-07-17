package top.simba1949.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 * @author v_jiayytian@tencent.com
 * @date 2018/7/11 18:19
 */
@RequestMapping("/jedis")
@RestController
public class SimpleJedisController {

    private Logger logger = LoggerFactory.getLogger(SimpleJedisController.class);

    /**
     * 简单redis使用
     * @param key
     * @param value
     * @return
     */
    @PostMapping
    public String setKey(String key,String value){
        // 获取 Redis 客户端，连接服务器
        Jedis jedis = new Jedis("127.0.0.1",6379);
        // 使用 redis 语法
        jedis.set(key,value);
        logger.info(key + value);
        // 释放资源
        jedis.close();
        return key + value;
    }

    @GetMapping
    public String getKey(String key){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        return jedis.get(key);
    }
}
