package com.pratifolio.SpringAI_Demo.Tools;

import com.pratifolio.SpringAI_Demo.Entity.HelpDeskTicket;
import com.pratifolio.SpringAI_Demo.ModelPOJO.TicketRequest;
import com.pratifolio.SpringAI_Demo.Service.ToolCall.HelpDeskTicketToolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HelpDeskTools {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelpDeskTools.class);
    private HelpDeskTicketToolService helpDeskTicketToolService;

    public HelpDeskTools(HelpDeskTicketToolService helpDeskTicketToolService) {
        this.helpDeskTicketToolService = helpDeskTicketToolService;
    }

    @Tool(
            name = "createTicket",
            description = "This tool will be called to create a Support Ticket"
    )
    String createTicket(@ToolParam(description = "Details to create a Support Ticket")TicketRequest ticketRequest,
                        ToolContext toolContext) {
        String username = (String) toolContext.getContext().get("username");
        LOGGER.info("Creating support ticket for user: {} with details: {}", username, ticketRequest);

        HelpDeskTicket createdTicket = helpDeskTicketToolService.createTicket(ticketRequest, username);
        LOGGER.info("Ticket created successfully. Ticket ID: {}, Username: {}", createdTicket.getId(), createdTicket.getUsername());
        return "Ticket #" + createdTicket.getId() + " created for user " + createdTicket.getUsername();

    }

    @Tool(
            name = "getTicketStatus",
            description = "This tool will be called to fetch the ticket status details based on the given username"
    )
    List<HelpDeskTicket> getTicketStatus(ToolContext toolContext) {
        String username = (String) toolContext.getContext().get("username");
        LOGGER.info("Fetching tickets for user: {}", username);
        List<HelpDeskTicket> tickets =  helpDeskTicketToolService.getTicketByUsername(username);
        LOGGER.info("Found {} tickets for user: {}", tickets.size(), username);
        // throw new RuntimeException("Unable to fetch ticket status");
        return tickets;
    }

}
