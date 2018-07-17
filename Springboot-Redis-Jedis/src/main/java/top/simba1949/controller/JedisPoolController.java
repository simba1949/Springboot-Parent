package top.simba1949.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author v_jiayytian@tencent.com
 * @date 2018/7/11 19:18
 */
@RequestMapping("/pool")
@RestController
public class JedisPoolController {

    private Logger logger = LoggerFactory.getLogger(JedisPoolController.class);

    @PostMapping
    public String setKeyByJedispool(String key,String value){
        // 获取连接池配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 设置最大连接数
        jedisPoolConfig.setMaxTotal(20);
        // 设置最大空闲连接数
        jedisPoolConfig.setMaxIdle(10);

        // 创建连接池
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);

        Jedis jedis = null;
        try {
            // 从连接池获取 Jedis 资源
            jedis = jedisPool.getResource();
            jedis.set(key,value);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
            if (jedisPool != null) {
                jedisPool.close();
            }
        }
        logger.info(key + value);
        return key + value;
    }

    @GetMapping
    public String getKeyByJedispool(String key){
        // 获取连接池配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 设置最大连接数
        jedisPoolConfig.setMaxTotal(20);
        // 设置最大空闲连接数
        jedisPoolConfig.setMaxIdle(10);

        // 创建连接池
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);

        Jedis jedis = null;
        String value = null;
        try {
            // 从连接池获取 Jedis 资源
            jedis = jedisPool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
            if (jedisPool != null) {
                jedisPool.close();
            }
        }
        logger.info(value);
        return value;
    }
}
