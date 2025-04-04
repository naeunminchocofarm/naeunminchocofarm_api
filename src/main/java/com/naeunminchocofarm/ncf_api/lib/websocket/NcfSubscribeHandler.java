package com.naeunminchocofarm.ncf_api.lib.websocket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public abstract class NcfSubscribeHandler {
    private static final Logger log = LogManager.getLogger(NcfSubscribeHandler.class);
    private final String destination;
    private final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    public NcfSubscribeHandler(String destination) {
        this.destination = destination;
    }

    public static NcfSubscribeHandler createDefault(String destination) {
        return new DefaultNcfSubscribeHandler(destination);
    }

    public String getDestination() {
        return destination;
    }

    public void subscribe(WebSocketSession session) {
        this.sessions.put(session.getId(), session);
    }

    public void broadcast(String body) {
        var responseFrame = new NcfFrame("MESSAGE", body);
        var responseMessage = new TextMessage(responseFrame.toString());
        this.sessions.values().forEach(x -> {
            try {
                x.sendMessage(responseMessage);
            } catch (IOException e) {
                log.error(e.getMessage());
                // throw new RuntimeException(e);
            }
        });
    }

    public void unsubscribe(WebSocketSession session) {
        this.sessions.remove(session.getId(), session);
    }

    public boolean isEmpty() {
        return this.sessions.isEmpty();
    }
}
