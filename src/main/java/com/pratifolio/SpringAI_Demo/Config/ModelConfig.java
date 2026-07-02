package com.pratifolio.SpringAI_Demo.Config;


import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfig {

    @Bean
    public ChatClient openAIChatClient(OpenAiChatModel openAiChatModel) {
        System.out.println(openAiChatModel.getDefaultOptions().getModel().toString());
        return ChatClient.builder(openAiChatModel).build();
    }

    @Bean
    public ChatClient anthropicChatClient(AnthropicChatModel anthropicChatModel) {
        System.out.println(anthropicChatModel.getDefaultOptions().getModel().toString());
        return ChatClient.builder(anthropicChatModel).build();
    }

}
