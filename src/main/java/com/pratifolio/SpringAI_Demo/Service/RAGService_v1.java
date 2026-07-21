package com.pratifolio.SpringAI_Demo.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RAGService_v1 {

    private final ChatClient OpenAIChatClient;
    private final VectorStore vectorStore;

    public RAGService_v1(@Qualifier("openAIMemoryChatClient") ChatClient OpenAIChatClient, VectorStore vectorStore) {
        this.OpenAIChatClient = OpenAIChatClient;
        this.vectorStore = vectorStore;
    }

    @Value("classpath:/promptTemplates/SystemPromptRandomDataTemplate.st")
    private Resource systemPromptRandomDataTemplate;

    public ResponseEntity<?> randomOpenAIChat(String username, String message) {
        SearchRequest searchRequest = SearchRequest.builder().query(message).topK(3).similarityThreshold(0.5).build();

        List<Document> similarDocs = vectorStore.similaritySearch(searchRequest);

        String similarContext = similarDocs
                .stream()
                .map(Document::getText)
                .collect(Collectors.joining(System.lineSeparator()));

        String answer = OpenAIChatClient.prompt()
                .system(
                        promptSystemSpec -> promptSystemSpec.text(systemPromptRandomDataTemplate)
                                .param("documents", similarContext)
                )
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, username))
                .user(message)
                .call().content();

        return new ResponseEntity<>(answer, HttpStatus.OK);
    }
}
