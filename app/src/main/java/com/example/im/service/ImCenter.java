package com.example.im.service;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class ImCenter {
    private WebSocketClient mClient;
    private OnLogListener mLogListener;

    public ImCenter(String ip) {
        URI uri = URI.create(ip);
        mClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                log(serverHandshake.toString());
            }

            @Override
            public void onMessage(String s) {
                log(s);
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                log(s + "|" + i + "|" + b);
            }

            @Override
            public void onError(Exception e) {
                log(e.getMessage());
            }
        };
    }

    public void setLogListener(OnLogListener listener) {
        mLogListener = listener;
    }

    public void connect() {
        if (mClient.isClosed()) {
            mClient.connect();
        }
    }

    public void disconnect() {
        if (mClient.isOpen()) {
            mClient.close();
        }
    }

    public void send(MessageSending msg) {
        mClient.send(msg.toString());
    }

    private void log(String content) {
        if (mLogListener != null) {
            mLogListener.log(content);
        }
    }
}
