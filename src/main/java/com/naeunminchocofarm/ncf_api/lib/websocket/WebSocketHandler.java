package com.naeunminchocofarm.ncf_api.lib.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketHandler extends TextWebSocketHandler  {
    private final NcfFrameHandler ncfFrameHandler;

    public WebSocketHandler(NcfFrameHandler ncfFrameHandler) {
        this.ncfFrameHandler = ncfFrameHandler;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        var receivedFrame = NcfFrame.parse(message.getPayload());

        this.ncfFrameHandler.handleFrame(session, receivedFrame);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        this.ncfFrameHandler.disconnect(session);
    }
}
