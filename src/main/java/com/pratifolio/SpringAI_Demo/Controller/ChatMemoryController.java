package com.pratifolio.SpringAI_Demo.Controller;

import com.pratifolio.SpringAI_Demo.Service.SimpleMemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChatMemoryController {

    @Autowired
    private SimpleMemService masterService_v2;

    @GetMapping("openAI-chatMemory/{msg}")
    public ResponseEntity<?> openAIChatMemory(@RequestHeader("username") String username, @PathVariable String msg) {
        return masterService_v2.openAIChatMemory(username, msg);
    }

}
