package com.naeunminchocofarm.ncf_api.lib.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final NcfFrameHandler ncfFrameHandler;

    public WebSocketConfig(NcfFrameHandler ncfFrameHandler) {
//        ncfFrameHandler.addSubscribeHandlers(
//                NcfSubscribeHandler.createDefault("test-subject"),
//                NcfSubscribeHandler.createDefault("motion-detecting"),
//                NcfSubscribeHandler.createDefault("awning")
//        );

        this.ncfFrameHandler = ncfFrameHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocketHandler(ncfFrameHandler), "/ws")
                .setAllowedOriginPatterns("*");
    }
}