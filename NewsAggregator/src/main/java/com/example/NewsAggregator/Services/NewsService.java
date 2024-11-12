package com.example.NewsAggregator.Services;

import com.example.NewsAggregator.Models.Article;
import com.example.NewsAggregator.Models.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public List<Article> getArticlesByTitle(String prefix)
    {
        return newsRepository.findByTitleContainingIgnoreCase(prefix);
    }
}
