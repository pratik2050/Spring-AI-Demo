package com.pratifolio.SpringAI_Demo.Service;


import org.springframework.ai.anthropic.AnthropicChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    private final ChatClient openAIChatClient;
    private final ChatClient anthropicChatClient;

    private final String customerSystemRole = """
            You are a professional customer service assistance which helps in drafting email responses
            to improve the productivity of the customer support team
                          """;

    @Value("classpath:/promptTemplates/customerEmailTemplate.st")
    private Resource customerEmailTemplate;

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

    public ResponseEntity<?> getEmailChat(String customerName, String customerMessage) {
        return new ResponseEntity<>(
          openAIChatClient
                  .prompt()
                  .system(customerSystemRole)
                  .user(promptUserSpec ->
                          promptUserSpec.text(customerEmailTemplate)
                                  .param("customerName", customerName)
                                  .param("customerMessage", customerMessage)
                          )
                  .call().content(), HttpStatus.OK);
    }
}
