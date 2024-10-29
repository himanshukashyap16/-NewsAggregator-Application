package com.example.NewsAggregator.Services;

import com.example.NewsAggregator.Models.NewsApiConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "newsapis")
@Getter
@Setter
public class NewsApiConfigLoader {
    private List<NewsApiConfig> configs;
}
