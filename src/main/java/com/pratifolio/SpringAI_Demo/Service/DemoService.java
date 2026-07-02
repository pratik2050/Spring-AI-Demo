package com.pratifolio.SpringAI_Demo.Service;


import org.springframework.ai.anthropic.AnthropicChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    private final ChatClient openAIChatClient;
    private final ChatClient anthropicChatClient;

    public DemoService(
            @Qualifier("openAIChatClient") ChatClient openAIChatClient,
            @Qualifier("anthropicChatClient") ChatClient anthropicChatClient
    ) {
        this.openAIChatClient = openAIChatClient;
        this.anthropicChatClient = anthropicChatClient;
    }


    public ResponseEntity<?> askOpenAI(String msg) {
        return new ResponseEntity<>(
                openAIChatClient.prompt(msg).call().content(),
                HttpStatus.OK
        );
    }

    public ResponseEntity<?> askAnthropic(String msg) {
        return new ResponseEntity<>(
                anthropicChatClient.prompt(msg).call().content(),
                HttpStatus.OK
        );
    }
}
