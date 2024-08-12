package com.example.redis;

import com.example.redis.Item;
import org.springframework.data.repository.CrudRepository;

//CrudRepository 로 Redis에 Hash 자료형으로 데이터를 저장
public interface ItemRepository extends CrudRepository<Item, Long> {

}
