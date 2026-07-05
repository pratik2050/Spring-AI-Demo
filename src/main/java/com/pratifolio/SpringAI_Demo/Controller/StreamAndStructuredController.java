package com.pratifolio.SpringAI_Demo.Controller;

import com.pratifolio.SpringAI_Demo.Service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class StreamAndStructuredController {

    @Autowired
    private MasterService demoService;

    @GetMapping("streamOpenAI/{msg}")
    public Flux<String> askOpenAI(@PathVariable String msg) {
        return demoService.streamOpenAI(msg);
    }

    @GetMapping("streamAnthropic/{msg}")
    public Flux<String> askGemini(@PathVariable String msg) {
        return demoService.streamAnthropic(msg);
    }


}
