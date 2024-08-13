package com.example.redis.services;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CacheService {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisTemplate<String, Object> addCache(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        return redisTemplate;
    }
    public Object get(String id){
        return redisTemplate.opsForValue().get(id);
    }

    public void delete(String id){
        redisTemplate.delete(id);
    }
}
