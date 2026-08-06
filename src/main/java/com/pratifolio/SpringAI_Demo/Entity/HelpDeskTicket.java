package com.pratifolio.SpringAI_Demo.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "helpdesk_tickets")
public class HelpDeskTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String issue;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime ETA;

    public HelpDeskTicket() {
    }

    public HelpDeskTicket(int id, String username, String issue, String status, LocalDateTime createdAt, LocalDateTime ETA) {
        this.id = id;
        this.username = username;
        this.issue = issue;
        this.status = status;
        this.createdAt = createdAt;
        this.ETA = ETA;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getETA() {
        return ETA;
    }

    public void setETA(LocalDateTime ETA) {
        this.ETA = ETA;
    }
}
