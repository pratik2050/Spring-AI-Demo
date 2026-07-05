package com.pratifolio.SpringAI_Demo.Config;


import com.pratifolio.SpringAI_Demo.Advisors.TokenUsageAuditAdvisor;
import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.anthropic.AnthropicChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfig {

    @Bean
    public ChatClient openAIChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient.builder(openAiChatModel)
                .defaultAdvisors(new TokenUsageAuditAdvisor())
                .build();
    }

    @Bean
    public ChatClient anthropicChatClient(AnthropicChatModel anthropicChatModel) {
//        var options = AnthropicChatOptions.builder()
//                .temperature(0.9)
//                .maxTokens(100)
//                .build();

        return ChatClient.builder(anthropicChatModel)
//                .defaultOptions(options)
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

}
