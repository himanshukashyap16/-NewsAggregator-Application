package com.example.NewsAggregator.NewsAggreagatorUtils;

import com.example.NewsAggregator.Models.NewsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;

public class NewsResponseSerde implements Serde<NewsResponse> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Serializer<NewsResponse> serializer() {
        return (topic, data) -> {
            try {
                return objectMapper.writeValueAsBytes(data);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error serializing NewsResponse", e);
            }
        };
    }

    @Override
    public Deserializer<NewsResponse> deserializer() {
        return (topic, data) -> {
            try {
                NewsResponse newsResponse= objectMapper.readValue(data, NewsResponse.class);
                return newsResponse;
            } catch (Exception e) {
                throw new RuntimeException("Error deserializing NewsResponse", e);
            }
        };
    }

}
