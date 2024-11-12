package com.example.NewsAggregator.Services;

import com.example.NewsAggregator.Models.NewsResponse;
import com.example.NewsAggregator.NewsAggreagatorUtils.NewsAggregatorConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@EnableKafka
@Component
public class KafkaConsumer {

    private final NewsWebSocketHandler newsWebSocketHandler;
    @Autowired
    private NewsEditServiceImpl editService;

    @Autowired
    public KafkaConsumer(NewsWebSocketHandler newsWebSocketHandler) {
        this.newsWebSocketHandler = newsWebSocketHandler;
    }


    @KafkaListener(topics = NewsAggregatorConstant.OUTPUT_TOPIC, groupId = "consumer-group_id")
    public void consumeNews(List<NewsResponse> newsResponseList) {
        for (NewsResponse newsResponse : newsResponseList) {
            //System.out.println(newsResponse);
            newsWebSocketHandler.sendNewsUpdate(newsResponse);
        }
    }
    @KafkaListener(topics = NewsAggregatorConstant.OUTPUT_TOPIC, groupId = "consumer-group_id")
    public void SaveNewsToDataBase(List<NewsResponse> newsResponseList) {
        for (NewsResponse newsResponse : newsResponseList) {
            //System.out.println(newsResponse);
            editService.saveNews(newsResponse);
        }
    }
}
