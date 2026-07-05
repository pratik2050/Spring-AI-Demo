package com.pratifolio.SpringAI_Demo.Service;


import com.pratifolio.SpringAI_Demo.Advisors.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class MasterService {

    private final ChatClient openAIChatClient;
    private final ChatClient anthropicChatClient;

    private final String customerSystemRole = """
            You are a professional customer service assistance which helps in drafting email responses
            to improve the productivity of the customer support team
                          """;

    @Value("classpath:/promptTemplates/CustomerEmailTemplate.st")
    private Resource customerEmailTemplate;

    @Value("classpath:/promptTemplates/SystemPromptTemplate.st")
    private Resource systemPromptTemplate;

    public MasterService(
            @Qualifier("openAIChatClient") ChatClient openAIChatClient,
            @Qualifier("anthropicChatClient") ChatClient anthropicChatClient
    ) {
        this.openAIChatClient = openAIChatClient;
        this.anthropicChatClient = anthropicChatClient;
    }


    public ResponseEntity<?> askOpenAI(String msg) {
        return new ResponseEntity<>(
                openAIChatClient
                        .prompt(msg)
                        .advisors(new SimpleLoggerAdvisor())
                        .call()
                        .content(),
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

    public ResponseEntity<?> getStuffedResponse(String customerMessage) {
        return new ResponseEntity<>(
                anthropicChatClient
                        .prompt()
                        .system(systemPromptTemplate)
                        .user(customerMessage)
                        .call().content(), HttpStatus.OK);
    }

    public Flux<String> streamOpenAI(String msg) {
        return openAIChatClient
                .prompt(msg)
                .stream()
                .content();
    }

    public Flux<String> streamAnthropic(String msg) {
        return anthropicChatClient
                .prompt(msg)
                .stream()
                .content();
    }
}
