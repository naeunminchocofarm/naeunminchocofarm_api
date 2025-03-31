package com.naeunminchocofarm.ncf_api.lib.websocket;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class NcfFrame {
    private final String command;
    private final Map<String, String> headers;
    private final String body;

    public NcfFrame(String command, Map<String, String> headers, String body) {
        this.command = command;
        headers.put("content-length", String.valueOf(body.length()));
        this.headers = headers;
        this.body = body;
    }

    public NcfFrame(String command, String body) {
        this(command, new HashMap<>(), body);
    }

    public NcfFrame(String command) {
        this(command, "");
    }

    public String getCommand() {
        return command;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        var frameJoiner = new StringJoiner("\n");
        frameJoiner.add(this.command);

        var headerJoiner = new StringJoiner("\n");
        this.headers.keySet().forEach(key -> {
            headerJoiner.add(String.format("%s:%s", key, this.headers.get(key)));
        });

        String rawHeaders = headerJoiner.toString();
        frameJoiner.add(rawHeaders);
        frameJoiner.add("");
        frameJoiner.add(this.body);

        return frameJoiner.toString();
    }

    public static NcfFrame parse(String rawFrame) {
        var lines = rawFrame.trim().split("\n");
        var command = lines[0].trim();

        var index = 1;
        var headers = new HashMap<String, String>();
        while (true) {
            var rawHeader = lines[index].trim();
            index += 1;

            if (rawHeader.isBlank()) {
                break;
            }

            var pair = rawHeader.split(":");
            headers.put(pair[0], pair[1]);
        }

        var bodyJoiner = new StringJoiner("\n");
        while (index < lines.length) {
            bodyJoiner.add(lines[index]);
            index += 1;
        }
        var body = bodyJoiner.toString().trim();

        return new NcfFrame(command, headers, body);
    }

    public String getDestination() {
        return this.headers.getOrDefault("destination", "");
    }
}
