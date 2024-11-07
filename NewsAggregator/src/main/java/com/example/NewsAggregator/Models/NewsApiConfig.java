package com.example.NewsAggregator.Models;


import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class NewsApiConfig {
    private String baseUrl;
    private String apiKey;
    private Map<String,String> defaultQueryParams;
    private long fetchInterval;
    private String paginationType;
    private String tableCode;

}
