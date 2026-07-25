package com.pratifolio.SpringAI_Demo.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WebSearchRagService {

    private ChatClient webSearchChatClient;

    public WebSearchRagService(@Qualifier("webSearchRAGChatClient") ChatClient webSearchChatClient) {
        this.webSearchChatClient = webSearchChatClient;
    }

    public ResponseEntity<?> openAiWebSearchChat(String username, String message) {
        return new ResponseEntity<>(
                webSearchChatClient.prompt()
                        .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, username))
                        .user(message)
                        .call().content()
                , HttpStatus.OK
        );
    }
}
