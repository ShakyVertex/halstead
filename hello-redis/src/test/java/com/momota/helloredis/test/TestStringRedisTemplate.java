package com.momota.helloredis.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class TestStringRedisTemplate {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testSet() {
        redisTemplate.opsForValue().set("key4", "value4");
    }

    @Test
    public void testGet() {
        String result = redisTemplate.opsForValue().get("key4");
        System.err.println(result);
    }

    @Test
    public void testDel() {
        redisTemplate.delete("key4");
    }
}