package com.example.NewsAggregator.Models;

import org.springframework.data.redis.core.StringRedisTemplate;

public interface PaginationStrategy {
    String getNextPage(String response, StringRedisTemplate stringRedisTemplate, String tableCode);
}
