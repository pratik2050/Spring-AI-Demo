package com.pratifolio.SpringAI_Demo.Controller;

import com.pratifolio.SpringAI_Demo.Service.MasterService_v1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PromptTemplateController {

    @Autowired
    private MasterService_v1 demoService;

    @GetMapping("email")
    public ResponseEntity<?> getEmailChat(@RequestParam String customerName, @RequestParam String customerMessage) {
        return demoService.getEmailChat(customerName, customerMessage);
    }

}
