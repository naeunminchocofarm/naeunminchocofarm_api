package com.naeunminchocofarm.ncf_api.lib.websocket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class NcfFrameHandler {
    private static final Logger log = LogManager.getLogger(NcfFrameHandler.class);
    private final List<NcfSubscribeHandler> subscribeHandlers = new ArrayList<>();

    public void addSubscribeHandlers(NcfSubscribeHandler... handlers) {
        this.subscribeHandlers.addAll(Arrays.stream(handlers).toList());
    }

    public void handleFrame(WebSocketSession session, NcfFrame frame) {
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
            case "MESSAGE":
                // 클라이언트가 서버로 메시지를 보낼 때는 항상 SEND 이용
                // MESSAGE는 서버가 클라이언트로 메시지 보낼 때 사용
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
        return this.subscribeHandlers.stream()
                .filter(x -> x.getDestination().equals(destination))
                .findFirst();
    }

    private void handleSubscribe(WebSocketSession session, NcfFrame frame) {
        var destination = frame.getDestination();
        var subscribeHandlerOptional = findSubscribeHandler(destination);
        var subscribeHandler = subscribeHandlerOptional.orElseGet(() -> {
            var newHandler = NcfSubscribeHandler.createDefault(destination);
            addSubscribeHandlers(newHandler);
            return newHandler;
        });

        subscribeHandler.subscribe(session);
        var responseFrame = new NcfFrame("SUBSCRIBE_SUCCESS");
        var responseMessage = new TextMessage(responseFrame.toString());
        try {
            session.sendMessage(responseMessage);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void disconnect(WebSocketSession session) {
        synchronized (this.subscribeHandlers) {
            var iterator = this.subscribeHandlers.iterator();
            while(iterator.hasNext()) {
                var subscribHandler = iterator.next();
                subscribHandler.unsubscribe(session);
                if (subscribHandler.isEmpty()) {
                    iterator.remove();
                }
            }
        }
    }
}
