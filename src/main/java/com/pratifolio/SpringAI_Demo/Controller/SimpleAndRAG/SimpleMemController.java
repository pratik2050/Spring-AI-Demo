package com.pratifolio.SpringAI_Demo.Controller.SimpleAndRAG;

import com.pratifolio.SpringAI_Demo.Service.SimpleAndRAG.SimpleMemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SimpleMemController {

    @Autowired
    private SimpleMemService masterService_v2;

    @GetMapping("openAI-chatMemory/{msg}")
    public ResponseEntity<?> openAIChatMemory(@RequestHeader("username") String username, @PathVariable String msg) {
        return masterService_v2.openAIChatMemory(username, msg);
    }

}
