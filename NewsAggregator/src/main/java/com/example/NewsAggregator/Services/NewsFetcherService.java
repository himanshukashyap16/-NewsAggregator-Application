package com.example.NewsAggregator.Services;

import com.example.NewsAggregator.NewsAggreagatorUtils.UrlBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Map;

public class NewsFetcherService {

    @Autowired
    private OkHttpService okHttpService;

    public String fetchNews(String baseUrl, Map<String,String> queryParams) throws IOException {
        String url = UrlBuilder.buildUrl(baseUrl, queryParams);
        return okHttpService.fetchData(url);

    }

}
