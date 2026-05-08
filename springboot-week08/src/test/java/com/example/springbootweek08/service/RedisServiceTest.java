package com.example.springbootweek08.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
public class RedisServiceTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 测试 StringRedisTemplate 模板的基本操作
     */
    @Test
    public void testStringTemplate() throws Exception {
        // 1. 存值：带过期时间（30秒）
        stringRedisTemplate.opsForValue().set("hello", "world", 30, TimeUnit.SECONDS);

        // 2. 存值：带分组key（用冒号分组，便于管理）
        stringRedisTemplate.opsForValue().set("code:13900001111", "1234");
        stringRedisTemplate.opsForValue().set("code:13900002222", "8899");

        // 3. 取值并打印
        String value = stringRedisTemplate.opsForValue().get("hello");
        log.info("Redis 字符串测试结果: {}", value);

        String code = stringRedisTemplate.opsForValue().get("code:13900001111");
        log.info("13900001111 验证码测试结果: {}", code);

        String code2 = stringRedisTemplate.opsForValue().get("code:13900002222");
        log.info("13900002222 验证码测试结果: {}", code2);
    }
}