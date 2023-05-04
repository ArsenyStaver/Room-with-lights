package com.example.demo.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.*;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;



// Аннотация, указывающая, что это класс конфигурации Spring
@Configuration
// Аннотация, включающая поддержку WebSocket в приложении
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    // Метод, который регистрирует WebSocket-обработчик и устанавливает разрешенные источники
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/socket").setAllowedOrigins("*");
    }

    // Метод, который создает и возвращает новый экземпляр TextWebSocketHandler
    private WebSocketHandler myHandler() {
        return new TextWebSocketHandler() {
            // Переменная, которая содержит набор активных WebSocket-сессий
            private final Set<WebSocketSession> sessions = new HashSet<>();

            // Метод, который вызывается после установки WebSocket-соединения
            // и добавляет новую WebSocket-сессию в переменную sessions
            @Override
            public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                sessions.add(session);
            }

            // Метод, который вызывается для обработки текстовых сообщений от клиента
            @Override
            protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
                String payload = message.getPayload();
                String currentRoomId = session.getUri().getQuery().split("=")[1]; // получаем ID текущей комнаты из URL-параметров
                for (WebSocketSession s : sessions) {
                    if (s != session) {
                        String roomId = s.getUri().getQuery().split("=")[1]; // получаем ID комнаты, к которой подключен сокет, из URL-параметров
                        if (roomId.equals(currentRoomId)) { // отправляем сообщение только тем сокетам, которые находятся в той же комнате, что и отправитель сообщения
                            s.sendMessage(new TextMessage(payload));
                        }
                    }
                }
            }

            // Метод, который вызывается после закрытия WebSocket-соединения
            // и удаляет закрытую WebSocket-сессию из переменной sessions
            @Override
            public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
                sessions.remove(session);
            }
        };
    }
}






