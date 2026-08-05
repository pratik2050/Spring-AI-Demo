package com.pratifolio.SpringAI_Demo.Config;


import com.pratifolio.SpringAI_Demo.Advisors.TokenUsageAuditAdvisor;
import com.pratifolio.SpringAI_Demo.Tools.TimeTools;
import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.anthropic.AnthropicChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ToolCallModelConfig {

    @Bean("timeCallOpenAIChatClient")
    public ChatClient openAIChatClient(OpenAiChatModel openAiChatModel, TimeTools timeTools) {
        Advisor loggerAdvisor = new SimpleLoggerAdvisor();
        Advisor tokenAdvisor = new TokenUsageAuditAdvisor();

        return ChatClient.builder(openAiChatModel)
                .defaultTools(timeTools)
                .defaultAdvisors(List.of(loggerAdvisor, tokenAdvisor))
                .build();
    }

    @Bean("toolCallAnthropicChatClient")
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

}
