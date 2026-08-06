package com.pratifolio.SpringAI_Demo.Service.ToolCall;

import com.pratifolio.SpringAI_Demo.Tools.HelpDeskTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HelpDeskTicketService {

    private ChatClient chatClient;
    private HelpDeskTools helpDeskTools;

    public HelpDeskTicketService(@Qualifier("helpDeskOpenAIChatClient") ChatClient chatClient,
                                     HelpDeskTools helpDeskTools) {
        this.chatClient = chatClient;
        this.helpDeskTools = helpDeskTools;
    }

    public ResponseEntity<?> getHelpDesk(String username, String message) {
        return new ResponseEntity<>(
                chatClient.prompt()
                        .user(message)
                        .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, username))
                        .tools(helpDeskTools)
                        .toolContext(Map.of("username", username))
                        .call().content()
                , HttpStatus.OK
        );
    }

}
