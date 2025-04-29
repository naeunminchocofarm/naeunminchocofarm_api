package com.naeunminchocofarm.ncf_api.lib.websocket;

import com.naeunminchocofarm.ncf_api.lib.jwt.JwtHandler;
import com.naeunminchocofarm.ncf_api.smart_farm.service.FarmService;
import io.jsonwebtoken.Claims;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class NcfFrameHandler {
    private static final Logger log = LogManager.getLogger(NcfFrameHandler.class);
    private final Set<NcfSubscribeHandler> concurrentSubscriberHandlerSet = new CopyOnWriteArraySet<>();
    private final JwtHandler jwtHandler;
    private final FarmService farmService;

    public NcfFrameHandler(JwtHandler jwtHandler, FarmService farmService) {
        this.jwtHandler = jwtHandler;
        this.farmService = farmService;
    }

    public void addSubscribeHandlers(NcfSubscribeHandler... handlers) {
        this.concurrentSubscriberHandlerSet.addAll(Arrays.stream(handlers).toList());
    }

    public void handleFrame(WebSocketSession session, NcfFrame frame) throws IOException {
        switch (frame.getCommand()) {
            case "SUBSCRIBE":
                handleSubscribe(session, frame);
                break;
            case "SEND":
                handleSend(frame);
                break;
            case "UNSUBSCRIBE":
                handleUnsubscribe(session, frame);
                break;
            default:
                break;
        }
    }

    private void handleUnsubscribe(WebSocketSession session, NcfFrame frame) {
        var subscribeHandler = findSubscribeHandler(frame.getDestination());
        subscribeHandler.ifPresent(x -> {
            x.unsubscribe(session);
        });
    }

    private void handleSend(NcfFrame frame) {
        var destination = frame.getDestination();
        var subscribeHandler = findSubscribeHandler(destination);
        subscribeHandler.ifPresent(x -> {
            x.broadcast(frame);
        });
    }

    private Optional<NcfSubscribeHandler> findSubscribeHandler(String destination) {
        return this.concurrentSubscriberHandlerSet.stream()
                .filter(x -> x.getDestination().equals(destination))
                .findFirst();
    }

    private void handleSubscribe(WebSocketSession session, NcfFrame frame) throws IOException {
        String auth = frame.getAuthorization();
        if (auth == null || !auth.startsWith("Bearer ")) {
            sendSubscribeFailed(session, "EMPTY_TOKEN");
            return;
        }

        String accessToken = auth.substring("Bearer ".length());
        var destination = frame.getDestination();
        try {
            Claims claims = jwtHandler.parseToken(accessToken);
            String roleName = claims.get("roleName", String.class);
            switch (roleName) {
                case "ROLE_FAMMER":
                    Integer memberId = jwtHandler.getId(claims);
                    Set<String> farmUuids = farmService.getFarmUuids(memberId);
                    if (!farmUuids.contains(destination)) {
                        sendSubscribeFailed(session, "INVALID_ROLE");
                        return;
                    }
                    break;
                case "ROLE_FARM":
                    String farmUuid = claims.get("uuid", String.class);
                    if (!farmUuid.equals(destination)) {
                        sendSubscribeFailed(session, "INVALID_ROLE");
                        return;
                    }
                    break;
                default:
                    sendSubscribeFailed(session, "INVALID_ROLE");
                    return;
            }
        } catch (Exception ex) {
            sendSubscribeFailed(session, "INVALID_TOKEN");
        }

        var subscribeHandlerOptional = findSubscribeHandler(destination);
        var subscribeHandler = subscribeHandlerOptional.orElseGet(() -> {
            var newHandler = NcfSubscribeHandler.createDefault(destination);
            addSubscribeHandlers(newHandler);
            return newHandler;
        });

        subscribeHandler.subscribe(session);
        sendFrame(session, new NcfFrame("SUBSCRIBE_SUCCESS"));
    }

    private void sendSubscribeFailed(WebSocketSession session, String reason) throws IOException {
        sendFrame(session, new NcfFrame("SUBSCRIBE_FAILED", new HashMap<>(), "INVALID_ROLE"));
    }

    private void sendFrame(WebSocketSession session, NcfFrame frame) throws IOException {
        session.sendMessage(new TextMessage(frame.toString()));
    }

    public void disconnect(WebSocketSession session) {
        for (NcfSubscribeHandler subscriberHandler : this.concurrentSubscriberHandlerSet) {
            subscriberHandler.unsubscribe(session);
        }
    }
}
