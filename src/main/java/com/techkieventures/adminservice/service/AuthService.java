package com.techkieventures.adminservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
public class AuthService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void storeToken(String username, String token) {
        redisTemplate.opsForValue().set(username, token, 1, TimeUnit.HOURS); // Store for 1 hour
    }

    public boolean validateToken(String username, String token) {
        String storedToken = redisTemplate.opsForValue().get(username);
        return storedToken != null && storedToken.equals(token);
    }

    public void deleteToken(String username) {
        redisTemplate.delete(username);
    }
}

