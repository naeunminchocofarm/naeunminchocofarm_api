package com.naeunminchocofarm.ncf_api.lib.websocket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

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

    public void broadcast(NcfFrame frame) {
        var closedSessionSet = new HashSet<WebSocketSession>();
        var responseFrame = new NcfFrame("MESSAGE", frame.getHeaders(), frame.getBody());
        var responseMessage = new TextMessage(responseFrame.toString());
        this.sessions.values().forEach(x -> {
            try {
                if (!x.isOpen()) {
                    closedSessionSet.add(x);
                    return;
                }
                x.sendMessage(responseMessage);
            } catch (IOException e) {
                closedSessionSet.add(x);
            }
        });
        closedSessionSet.forEach(x -> {
            sessions.remove(x.getId(), x);
        });
    }

    public void unsubscribe(WebSocketSession session) {
        this.sessions.remove(session.getId(), session);
    }

    public boolean isEmpty() {
        return this.sessions.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof NcfSubscribeHandler that)) return false;
        return Objects.equals(destination, that.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(destination);
    }
}
