package com.example.NewsAggregator.Models;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class News {

    @Id
    private Integer newsId;

    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;

    @JsonAlias({"url", "source_url"})
    private String sourceUrl;

    @JsonAlias({"urlToImage", "image_url"})
    private String imageUrl;
    @JsonProperty("publishedAt")
    private String publishedTime;
    @JsonProperty("content")
    private String content;

    public News() {
    }


    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", publishedTime='" + publishedTime + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
