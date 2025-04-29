package com.naeunminchocofarm.ncf_api.lib.websocket;

import com.naeunminchocofarm.ncf_api.lib.jwt.JwtHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;

@Component
public class AuthWebSocketHandler extends TextWebSocketHandler {
    private final NcfFrameHandler ncfFrameHandler;
    private final JwtHandler jwtHandler;

    public AuthWebSocketHandler(NcfFrameHandler ncfFrameHandler, JwtHandler jwtHandler) {
        this.ncfFrameHandler = ncfFrameHandler;
        this.jwtHandler = jwtHandler;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        session.getAttributes().put("authenticated", false);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        var receivedFrame = NcfFrame.parse(message.getPayload());
        if (isAuthenticated(session)) {
            this.ncfFrameHandler.handleFrame(session, receivedFrame);
            return;
        }

        if (!authenticate(session, receivedFrame)) {
            sendAuthFailMessage(session);
            session.close();
            return;
        }

        sendAuthSuccessMessage(session);
    }

    private void sendAuthSuccessMessage(WebSocketSession session) throws IOException {
        var authSuccessFrame = new NcfFrame("AUTH_SUCCESS", new HashMap<>(), "");
        var authSuccessMessage = new TextMessage(authSuccessFrame.toString());
        session.sendMessage(authSuccessMessage);
    }

    private boolean isAuthenticated(WebSocketSession session) {
        return session.getAttributes().get("authenticated").equals(true);
    }

    private boolean authenticate(WebSocketSession session, NcfFrame receivedFrame) throws IOException {
        if (!receivedFrame.getCommand().equals("AUTHENTICATE")) {
            return false;
        }

        try {
            var auth = receivedFrame.getHeaders().getOrDefault("Authorization", null);
            if (auth == null || !auth.startsWith("Bearer ")) {
                return false;
            }

            var accessToken = auth.substring("Bearer ".length());
            jwtHandler.parseToken(accessToken);
        } catch (Exception ex) {
            return false;
        }

        session.getAttributes().put("authenticated", true);
        return true;
    }

    private void sendAuthFailMessage(WebSocketSession session) throws IOException {
        var responseFrame = new NcfFrame("AUTH_FAIL", new HashMap<>(), "");
        var responseMessage = new TextMessage(responseFrame.toString());
        session.sendMessage(responseMessage);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }
}
