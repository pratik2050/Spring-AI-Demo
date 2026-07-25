package com.pratifolio.SpringAI_Demo.Controller;

import com.pratifolio.SpringAI_Demo.Service.QDrantRagService;
import com.pratifolio.SpringAI_Demo.Service.WebSearchRagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rag")
public class RAGController {

    @Autowired
    private QDrantRagService ragService_v1;

    @Autowired
    private WebSearchRagService webSearchRagService;

    @GetMapping("random/openAIChat")
    public ResponseEntity<?> randomOpenAIChat(@RequestHeader("username") String username, @RequestParam("message") String message) {
        return ragService_v1.randomOpenAIChat(username, message);
    }

    @GetMapping("random/openAIDocsChat")
    public ResponseEntity<?> randomOpenAIDocsChat(@RequestHeader("username") String username, @RequestParam("message") String message) {
        return ragService_v1.randomOpenAIDocsChat(username, message);
    }

    @GetMapping("web-search/chat")
    public ResponseEntity<?> openAiWebSearchChat(@RequestHeader("username") String username, @RequestParam("message") String message) {
        return webSearchRagService.openAiWebSearchChat(username, message);
    }

}
