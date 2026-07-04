package com.pratifolio.SpringAI_Demo.Controller;

import com.pratifolio.SpringAI_Demo.Service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    @Autowired
    private MasterService demoService;

    @GetMapping("askOpenAI/{msg}")
    public ResponseEntity<?> askOpenAI(@PathVariable String msg) {
        return demoService.askOpenAI(msg);
    }

    @GetMapping("askAnthropic/{msg}")
    public ResponseEntity<?> askGemini(@PathVariable String msg) {
        return demoService.askAnthropic(msg);
    }

}
