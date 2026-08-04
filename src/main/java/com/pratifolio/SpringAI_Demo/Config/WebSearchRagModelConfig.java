package com.pratifolio.SpringAI_Demo.Config;

import com.pratifolio.SpringAI_Demo.Advisors.TokenUsageAuditAdvisor;
import com.pratifolio.SpringAI_Demo.RAG.WebSearchDocumentRetriever;
import org.springframework.ai.chat.cache.semantic.SemanticCacheAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.preretrieval.query.transformation.TranslationQueryTransformer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.util.List;

@Configuration
public class WebSearchRagModelConfig {

    @Bean("webSearchRAGChatClient")
    public ChatClient chatClient(OpenAiChatModel openAiChatModel, SemanticCacheAdvisor semanticCacheAdvisor,
                                 ChatMemory chatMemory, RestClient.Builder restClientBuilder, ChatClient.Builder builder) {

        Advisor loggerAdvisor = new SimpleLoggerAdvisor();
        Advisor tokenUsageAdvisor = new TokenUsageAuditAdvisor();
        Advisor memoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        var webSearchRAGAdvisor = RetrievalAugmentationAdvisor.builder()
                .documentRetriever(WebSearchDocumentRetriever.builder()
                        .restClientBuilder(restClientBuilder).maxResults(5).build())
                .build();

        return ChatClient.builder(openAiChatModel)
                .defaultAdvisors(List.of(loggerAdvisor, memoryAdvisor, tokenUsageAdvisor,
                        webSearchRAGAdvisor, semanticCacheAdvisor))
                .build();
    }

}
