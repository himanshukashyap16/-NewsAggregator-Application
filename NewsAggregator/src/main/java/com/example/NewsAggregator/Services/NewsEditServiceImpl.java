package com.example.NewsAggregator.Services;

import com.example.NewsAggregator.Models.Article;
import com.example.NewsAggregator.Models.NewsRepository;
import com.example.NewsAggregator.Models.NewsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsEditServiceImpl {

    @Autowired
    private NewsRepository newsRepository;


    public void  saveNews(NewsResponse newsResponse)
    {
        System.out.println("Inserting into News Databases");
        List<Article> articles = newsRepository.saveAll(newsResponse.getArticles());
        for(int start=0; start< articles.size();start++)
        {
            System.out.println(articles.get(start).getArticleId());
        }
    }
}
