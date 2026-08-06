package com.pratifolio.SpringAI_Demo.Service.ToolCall;

import com.pratifolio.SpringAI_Demo.Entity.HelpDeskTicket;
import com.pratifolio.SpringAI_Demo.ModelPOJO.TicketRequest;
import com.pratifolio.SpringAI_Demo.Repository.HelpDeskTicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HelpDeskTicketToolService {

    @Autowired
    private HelpDeskTicketRepo helpDeskTicketRepo;

    public HelpDeskTicket createTicket(TicketRequest ticketRequest, String username) {
        HelpDeskTicket ticket = new HelpDeskTicket();

        ticket.setIssue(ticketRequest.issue());
        ticket.setUsername(username);
        ticket.setStatus("OPEN");
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setETA(LocalDateTime.now().plusDays(7));

        return helpDeskTicketRepo.save(ticket);
    }

    public List<HelpDeskTicket> getTicketByUsername(String username) {
        return helpDeskTicketRepo.findByUsername(username);
    }

}
