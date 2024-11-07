package com.example.NewsAggregator.Models;

import org.springframework.data.redis.core.StringRedisTemplate;

public class NextPagePageStrategy implements PaginationStrategy{
    @Override
    public String getNextPage(String response, StringRedisTemplate stringRedisTemplate, String tableCode) {
        String key = "\"nextPage\":\"";
        int startIndex= response.indexOf(key);

        if(startIndex!=-1)
        {
            startIndex+=key.length();
            int endIndex= response.indexOf("\"", startIndex);
            if(endIndex!=-1){
                return response.substring(startIndex, endIndex);
            }
        }
        return null;
    }
}
