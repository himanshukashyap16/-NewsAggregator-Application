package com.example.NewsAggregator.Services;

import com.example.NewsAggregator.Models.IncrementalPageStrategy;
import com.example.NewsAggregator.Models.NewsApiConfig;
import com.example.NewsAggregator.Models.NextPagePageStrategy;
import com.example.NewsAggregator.Models.PaginationStrategy;
import com.example.NewsAggregator.NewsAggreagatorUtils.NewsAggregatorConstant;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.data.redis.core.StringRedisTemplate;

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
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @PostConstruct
    public void init() {
        stringRedisTemplate.getConnectionFactory().getConnection().flushDb();
        if (newsApiConfigLoader != null && newsApiConfigLoader.getConfigs() != null) {
            for (NewsApiConfig newsApiConfig : newsApiConfigLoader.getConfigs()) {
                threadPoolTaskScheduler.scheduleAtFixedRate(() -> fetchNewsFromConfig(newsApiConfig), newsApiConfig.getFetchInterval());
            }
        }
    }


    public void fetchNewsFromConfig(NewsApiConfig newsApiConfig) {
        String nextPageKey = "nextPage:" + newsApiConfig.getTableCode();
        PaginationStrategy paginationStrategy = getNextPageStrategy(newsApiConfig);
        Map<String, String> queryParams = new HashMap<>(newsApiConfig.getDefaultQueryParams());
        queryParams.put("apikey", newsApiConfig.getApiKey());
        String nextPageValue = stringRedisTemplate.opsForValue().get(nextPageKey);
        if (nextPageValue != null) {
            queryParams.put("page", nextPageValue);
        }

        try {
            String response = newsFetcherService.fetchNews(newsApiConfig.getBaseUrl(), queryParams);
//            System.out.println(nextPageKey);
//            System.out.println(response);
            String newNextPageValue = paginationStrategy.getNextPage(response, stringRedisTemplate, nextPageKey);
            if (newNextPageValue != null) {
                stringRedisTemplate.opsForValue().set(nextPageKey, newNextPageValue);
            }

            kafkaTemplate.send(NewsAggregatorConstant.INPUT_TOPIC, response);
        } catch (IOException ignored) {
        }
    }

    private PaginationStrategy getNextPageStrategy(NewsApiConfig newsApiConfig) {
        if (newsApiConfig.getPaginationType().equalsIgnoreCase("INCREMENTAL"))
            return new IncrementalPageStrategy();
        else
            return new NextPagePageStrategy();
    }
}
