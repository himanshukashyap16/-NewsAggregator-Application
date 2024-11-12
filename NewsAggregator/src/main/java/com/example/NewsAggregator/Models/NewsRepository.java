package com.example.NewsAggregator.Models;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NewsRepository extends MongoRepository<Article, String> {



    List<Article> findByTitleContainingIgnoreCase(String prefix);
}
