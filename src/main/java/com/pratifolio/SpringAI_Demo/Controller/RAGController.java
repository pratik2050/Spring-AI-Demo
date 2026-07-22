package com.pratifolio.SpringAI_Demo.Controller;

import com.pratifolio.SpringAI_Demo.Service.RAGService_v1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rag")
public class RAGController {

    @Autowired
    private RAGService_v1 ragService_v1;

    @GetMapping("random/openAIChat")
    public ResponseEntity<?> randomOpenAIChat(@RequestHeader("username") String username, @RequestParam("message") String message) {
        return ragService_v1.randomOpenAIChat(username, message);
    }

    @GetMapping("random/openAIDocsChat")
    public ResponseEntity<?> randomOpenAIDocsChat(@RequestHeader("username") String username, @RequestParam("message") String message) {
        return ragService_v1.randomOpenAIDocsChat(username, message);
    }

}
