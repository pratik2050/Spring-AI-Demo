package com.pratifolio.SpringAI_Demo.Service.ToolCall;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TimeService {

    private ChatClient chatClient;

    public TimeService(@Qualifier("timeCallOpenAIChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    public ResponseEntity<?> getLocalTime(String message) {
        return new ResponseEntity<>(
                chatClient.prompt()
                        .user(message)
                        .call().content()
                , HttpStatusCode.valueOf(200)
        );
    }
}
