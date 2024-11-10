package com.example.NewsAggregator.Services;

import com.example.NewsAggregator.Models.NewsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class NewsWebSocketHandler extends TextWebSocketHandler {
    private final List<WebSocketSession> webSocketSessionList = new ArrayList<>();
    private final List<NewsResponse> pendingNews = new ArrayList<>();


    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        System.out.println("WebSocket connection established: " + webSocketSession.getId());
        webSocketSessionList.add(webSocketSession);

        for(NewsResponse news: pendingNews)
        {
            try{
                System.out.println(news);
                String newsResponseJson= convertNewsToJson(news);
                System.out.println(newsResponseJson);
                webSocketSession.sendMessage(new TextMessage(newsResponseJson));
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        //pendingNews.clear();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus){
        System.out.println("WebSocket connection remove: " + webSocketSession.getId());
        webSocketSessionList.remove(webSocketSession);
    }




    public void sendNewsUpdate(NewsResponse news)
    {

        if (webSocketSessionList.isEmpty()) {
            System.out.println("No active WebSocket sessions. Unable to send update.");
            pendingNews.add(news);
        }

        for(WebSocketSession webSocketSession: webSocketSessionList) {
            if (webSocketSession.isOpen())
            {
                try{
                    System.out.println(news);
                    String newsResponseJson= convertNewsToJson(news);
                    System.out.println(newsResponseJson);
                    webSocketSession.sendMessage(new TextMessage(newsResponseJson));
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
    }

    private String convertNewsToJson(NewsResponse newsResponse)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            return objectMapper.writeValueAsString(newsResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
