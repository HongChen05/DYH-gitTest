package com.example.springbootweek08;

import com.alibaba.fastjson2.JSON;
import com.example.springbootweek08.entity.Address;
import com.example.springbootweek08.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 测试 RedisTemplate 模板的基本操作
     */
    @Test
    public void testRedisTemplate() throws Exception {
        // 测试字符串操作，有效时间20秒
        redisTemplate.opsForValue().set("code:13900003333", "1234", 20, TimeUnit.SECONDS);
        String code = Objects.requireNonNull(redisTemplate.opsForValue().get("code:13900003333")).toString();
        log.info("13900003333 验证码测试结果: {}", code);

        // 测试对象操作
        Address address = new Address();
        address.setCity("南京市");
        address.setStreet("栖霞区羊山北路1号");
        address.setZipCode("210000");

        User user = new User();
        user.setName("张三");
        user.setAge(22);
        user.setEmail("zhangsan@qq.com");
        user.setAddress(address);

        // 将对象存入 Redis
        redisTemplate.opsForValue().set("user:001", user);

        Object userObj = redisTemplate.opsForValue().get("user:001");

        // 反序列化
        User user2 = JSON.parseObject(JSON.toJSONString(userObj), User.class);
        log.info("user:001 测试结果: {}", user2);
    }
}