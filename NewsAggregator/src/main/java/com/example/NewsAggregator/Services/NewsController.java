package com.example.NewsAggregator.Services;

import com.example.NewsAggregator.Models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("/search")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Article> getNewsByTitle(String prefix)
    {
        return newsService.getArticlesByTitle(prefix);
    }
}
