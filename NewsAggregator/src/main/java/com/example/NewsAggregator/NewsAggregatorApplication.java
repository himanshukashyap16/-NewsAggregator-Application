package com.example.NewsAggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
@ImportResource("classpath:applicationContext.xml")
public class NewsAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsAggregatorApplication.class, args);
	}

}
