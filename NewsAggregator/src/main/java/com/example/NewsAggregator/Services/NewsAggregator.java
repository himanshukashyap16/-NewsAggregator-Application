package com.example.NewsAggregator.Services;

import com.example.NewsAggregator.Models.NewsApiConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NewsAggregator {
    @Autowired
    private NewsFetcherService newsFetcherService;
    @Autowired
    private NewsApiConfigLoader newsApiConfigLoader;
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @PostConstruct
    public void init(){

        System.out.println(newsApiConfigLoader);
        if(newsApiConfigLoader!=null && newsApiConfigLoader.getConfigs()!=null) {
            for (NewsApiConfig newsApiConfig : newsApiConfigLoader.getConfigs()) {
            threadPoolTaskScheduler.scheduleAtFixedRate(()-> fetchNewsFromConfig(newsApiConfig), newsApiConfig.getFetchInterval());
            }
        }
    }


    public void fetchNewsFromConfig(NewsApiConfig newsApiConfig)
    {
        Map<String,String> queryParams = new HashMap<>(newsApiConfig.getDefaultQueryParams());
        queryParams.put("apikey", newsApiConfig.getApiKey());

        try{
            String response = newsFetcherService.fetchNews(newsApiConfig.getBaseUrl(), queryParams);
            System.out.println(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
