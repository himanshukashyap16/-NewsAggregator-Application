package com.example.NewsAggregator.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final NewsWebSocketHandler newsWebSocketHandler;


    @Autowired
    public  WebSocketConfig(NewsWebSocketHandler newsWebSocketHandler)
    {
        this.newsWebSocketHandler = newsWebSocketHandler;
    }
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(newsWebSocketHandler, "/news-updates")
                .setAllowedOrigins("http://localhost:3000"); // List specific origins here
//                .withSockJS();
    }
}
