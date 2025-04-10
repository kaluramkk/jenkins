package com.techkieventures.adminservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void saveSession(String username, String token) {
        redisTemplate.opsForValue().set(username, token, 1, TimeUnit.HOURS);
    }

    public String getSession(String username) {
        return redisTemplate.opsForValue().get(username);
    }

    public void removeSession(String username) {
        redisTemplate.delete(username);
    }
}

