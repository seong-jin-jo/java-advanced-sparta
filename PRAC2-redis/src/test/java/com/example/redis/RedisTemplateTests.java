package com.example.redis;

import com.example.redis.redisTemplate.ItemDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTemplateTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate; // string 데이터타입 처리를 위함

    @Qualifier("itemRedisTemplate")
    @Autowired
    private RedisTemplate itemRedisTemplate; // 다양한 데이터타입 처리를 위함

    /**
     * String 을 Redis에 저장조회
     */
    @Test
    public void stringOpsTest() {

    }

    /**
     * Key-Value 를 Redis에 저장조회
     */
    @Test
    public void stringValueOpsTest() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("simplekey", "simplevalue");
        System.out.println(ops.get("simplekey"));
        ops.set("greeting", "hello redis!");
        System.out.println(ops.get("greeting"));
    }

    /**
     * Set 을 Redis에 저장조회
     */
    @Test
    public void stringSetOpsTest() {
        SetOperations<String, String> setOps = stringRedisTemplate.opsForSet();
        setOps.add("hobbies", "games");
        setOps.add("hobbies", "coding");
        setOps.add("hobbies", "alcohol");
        setOps.add("hobbies", "games");
        System.out.println(setOps.size("hobbies"));
    }

    /**
     * Redis 유효기간 테스트
     */
    @Test
    public void redisOpsTest() {
        stringRedisTemplate.expire("simplekey", 5, TimeUnit.SECONDS);
        stringRedisTemplate.expire("greeting", 10, TimeUnit.SECONDS);
        stringRedisTemplate.expire("hobbies", 15, TimeUnit.SECONDS);
    }

    /**
     * 객체를 Redis 에 저장조회
     */
    @Test
    public void itemRedisTemplateTest() {
        ValueOperations<String, ItemDto> ops = itemRedisTemplate.opsForValue();
        ops.set("my:keyboard", ItemDto.builder()
                .name("Mechanical Keyboard")
                .price(300000)
                .description("Expensive 😢")
                .build());
        System.out.println(ops.get("my:keyboard"));

        ops.set("my:mouse", ItemDto.builder()
                .name("mouse mice")
                .price(100000)
                .description("Expensive 😢")
                .build());
        System.out.println(ops.get("my:mouse"));
    }
}