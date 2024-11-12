package com.example.NewsAggregator.Models;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Article {
    @Id
    private String articleId;
    private Source source;
    private String author;
    private String title;
    private String description;
    @JsonAlias({"link", "url"})
    private String url;

    @JsonAlias({"image_url", "urlToImage"})
    private String urlToImage;
    private String publishedAt;
    private String content;

}

