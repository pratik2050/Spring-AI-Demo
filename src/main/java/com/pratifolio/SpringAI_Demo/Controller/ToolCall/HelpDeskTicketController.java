package com.pratifolio.SpringAI_Demo.Controller.ToolCall;

import com.pratifolio.SpringAI_Demo.Service.ToolCall.HelpDeskTicketService;
import com.pratifolio.SpringAI_Demo.Service.ToolCall.HelpDeskTicketToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tools")
public class HelpDeskTicketController {

    @Autowired
    private HelpDeskTicketService helpDeskTicketService;

    @GetMapping("help-desk")
    public ResponseEntity<?> helpDesk(@RequestHeader("username") String username,
                                      @RequestParam("message") String message) {
        return helpDeskTicketService.getHelpDesk(username, message);
    }

}
