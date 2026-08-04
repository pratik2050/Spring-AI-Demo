package com.pratifolio.SpringAI_Demo.Controller.SimpleAndRAG;

import com.pratifolio.SpringAI_Demo.Service.SimpleAndRAG.SimpleNonMemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PromptStuffingController {

    @Autowired
    private SimpleNonMemService demoService;

    @GetMapping("prompt-stuffing")
    public ResponseEntity<?> getStuffedResponse(@RequestParam String customerMessage) {
        return demoService.getStuffedResponse(customerMessage);
    }

}
