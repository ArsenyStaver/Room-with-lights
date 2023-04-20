package com.example.demo.controllers;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashSet;

import java.util.Set;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/socket").setAllowedOrigins("*");
    }

    private WebSocketHandler myHandler() {
        return new TextWebSocketHandler() {
            private final Set<WebSocketSession> sessions = new HashSet<>();

            @Override
            public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                sessions.add(session);
            }

            @Override
            protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
                String payload = message.getPayload();
                for (WebSocketSession s : sessions) {
                    if (s != session) {
                        s.sendMessage(new TextMessage(payload));
                    }
                }
            }

            @Override
            public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
                sessions.remove(session);
            }
        };
    }

}







