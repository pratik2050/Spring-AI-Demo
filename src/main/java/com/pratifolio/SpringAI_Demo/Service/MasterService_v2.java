package com.pratifolio.SpringAI_Demo.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MasterService_v2 {

    private final ChatClient openAiMemoryChatClient;

    public MasterService_v2(@Qualifier("openAIMemoryChatClient") ChatClient chatClient) {
           this.openAiMemoryChatClient = chatClient;
    }

    public ResponseEntity<?> openAIChatMemory(String username, String msg) {
        return new ResponseEntity<>(
                openAiMemoryChatClient
                        .prompt()
                        .user(msg)
                        .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, username))
                        .call()
                        .content()
                , HttpStatus.OK
        );
    }
}
