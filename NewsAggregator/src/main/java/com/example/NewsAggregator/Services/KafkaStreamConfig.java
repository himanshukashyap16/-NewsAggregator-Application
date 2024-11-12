package com.example.NewsAggregator.Services;

import com.example.NewsAggregator.Models.NewsResponse;
import com.example.NewsAggregator.NewsAggreagatorUtils.NewsAggregatorConstant;
import com.example.NewsAggregator.NewsAggreagatorUtils.NewsResponseSerde;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class KafkaStreamConfig {
    @Autowired
    private KafkaProperties kafkaProperties;
    @Autowired
    private KafkaTemplate<String, NewsResponse> kafkaTemplate;

    @Bean
    public KafkaStreams kStreamConfig(StreamsBuilder streamsBuilder)
    {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "news-stream-processor");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaProperties.getBootstrapServers());

        KStream<String,String> inputStream = streamsBuilder.stream(NewsAggregatorConstant.INPUT_TOPIC,
                Consumed.with(Serdes.String(),Serdes.String()));
        ObjectMapper objectMapper = new ObjectMapper();


        KStream<String,NewsResponse> newsStream = inputStream.mapValues(value -> {
            try {
                NewsResponse newsResponse = objectMapper.readValue(value, NewsResponse.class);
                return  newsResponse;
            } catch (JsonProcessingException e) {
                return  null;
            }
        });

        NewsResponseSerde newsResponseSerde = new NewsResponseSerde();
        newsStream.to(NewsAggregatorConstant.OUTPUT_TOPIC, Produced.with(Serdes.String(), newsResponseSerde));

        KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), properties);
        kafkaStreams.start();
        Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));

        return kafkaStreams;

    }

}
