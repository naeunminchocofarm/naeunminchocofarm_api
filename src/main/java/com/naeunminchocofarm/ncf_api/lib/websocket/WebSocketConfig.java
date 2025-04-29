package com.naeunminchocofarm.ncf_api.lib.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final WebSocketHandler webSocketHandler;
    private final AuthWebSocketHandler authWebSocketHandler;

    public WebSocketConfig(WebSocketHandler webSocketHandler, AuthWebSocketHandler authWebSocketHandler) {
        this.webSocketHandler = webSocketHandler;
        this.authWebSocketHandler = authWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/ws")
                .setAllowedOriginPatterns("*");

        registry.addHandler(authWebSocketHandler, "/aws")
                .setAllowedOriginPatterns("*");
    }
}