package com.pratifolio.SpringAI_Demo.Controller.ToolCall;

import com.pratifolio.SpringAI_Demo.Service.ToolCall.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tools")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @GetMapping("local-time")
    public ResponseEntity<?> getLocalTime(@RequestParam("message") String message) {
        return timeService.getLocalTime(message);
    }

}
