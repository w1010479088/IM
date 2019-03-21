package com.example.im.service;

public class MessageSending {
    private String content;

    public MessageSending(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
