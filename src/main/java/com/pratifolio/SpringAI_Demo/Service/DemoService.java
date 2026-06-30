package com.pratifolio.SpringAI_Demo.Service;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    private final ChatClient openAIChatClient;
    private final ChatClient bedrockChatClient;

    public DemoService(
            @Qualifier("openAIChatClient") ChatClient openAIChatClient,
            @Qualifier("bedrockChatClient") ChatClient bedrockChatClient
    ) {
        this.openAIChatClient = openAIChatClient;
        this.bedrockChatClient = bedrockChatClient;
    }


    public ResponseEntity<?> askOpenAI(String msg) {
        return new ResponseEntity<>(
                openAIChatClient.prompt(msg).call().content(),
                HttpStatus.OK
        );
    }

    public ResponseEntity<?> askBedrock(String msg) {
        return new ResponseEntity<>(
                bedrockChatClient.prompt(msg).call().content(),
                HttpStatus.OK
        );
    }
}
