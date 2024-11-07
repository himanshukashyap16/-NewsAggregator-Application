package com.example.NewsAggregator.Models;

import org.springframework.data.redis.core.StringRedisTemplate;

public class IncrementalPageStrategy implements PaginationStrategy {
    @Override
    public String getNextPage(String response, StringRedisTemplate stringRedisTemplate, String tableCode) {
        if(stringRedisTemplate.opsForValue().get(tableCode)!=null)
        {
            String nextPageValue= stringRedisTemplate.opsForValue().get(tableCode);
            if(nextPageValue!=null) {
                int nextPage= Integer.parseInt(nextPageValue) + 1;
                return Integer.toString(nextPage);
            }
        }
        return "1";
    }
}
