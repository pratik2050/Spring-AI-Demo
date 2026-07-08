package com.pratifolio.SpringAI_Demo.Config;


import com.pratifolio.SpringAI_Demo.Advisors.TokenUsageAuditAdvisor;
import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.anthropic.AnthropicChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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
        var options = AnthropicChatOptions.builder()
                .temperature(0.9)
                .maxTokens(100)
                .build();

        return ChatClient.builder(anthropicChatModel)
                .defaultOptions(options)
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    @Bean
    public ChatClient openAIMemoryChatClient(OpenAiChatModel openAiChatModel, ChatMemory chatMemory) {
        Advisor memoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        Advisor loggerAdvisor = new SimpleLoggerAdvisor();

        return ChatClient.builder(openAiChatModel)
                .defaultAdvisors(List.of(loggerAdvisor, memoryAdvisor))
                .build();
    }

}
