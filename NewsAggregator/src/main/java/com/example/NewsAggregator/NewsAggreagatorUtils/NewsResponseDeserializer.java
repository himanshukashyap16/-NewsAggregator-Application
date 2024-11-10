package com.example.NewsAggregator.NewsAggreagatorUtils;

import com.example.NewsAggregator.Models.NewsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import java.util.Map;

public class NewsResponseDeserializer implements Deserializer<NewsResponse> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}

    @Override
    public NewsResponse deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, NewsResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing NewsResponse", e);
        }
    }

    @Override
    public void close() {}
}
