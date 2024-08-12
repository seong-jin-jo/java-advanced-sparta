package com.example.redis;// package, import 생략

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("item") // Entity가 아닌 RedisHash 를 썼다
public class Item implements Serializable {
    @Id
    private Long id;
    private String name;
    private String description;
    private Integer price;
}