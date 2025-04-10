package com.techkieventures.adminservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

    @Value("${redis.host}") // Read Redis host from properties
    private String redisHost;

    @Value("${redis.port}") // Read Redis port from properties
    private int redisPort;

    @Value("${redis.maxTotal:10}") // Default max connections to 10
    private int maxTotal;

    @Value("${redis.maxIdle:5}") // Default max idle connections to 5
    private int maxIdle;

    @Value("${redis.minIdle:2}") // Default min idle connections to 2
    private int minIdle;

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setJmxEnabled(false);  

        return new JedisPool(poolConfig, redisHost, redisPort);
    }

//    @Bean
//    public Jedis jedis(JedisPool jedisPool) {
//        return jedisPool.getResource();
//    }
//    
   
}
