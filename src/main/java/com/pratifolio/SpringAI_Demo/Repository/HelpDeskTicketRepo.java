package com.pratifolio.SpringAI_Demo.Repository;

import com.pratifolio.SpringAI_Demo.Entity.HelpDeskTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelpDeskTicketRepo extends JpaRepository<HelpDeskTicket, Integer> {

    List<HelpDeskTicket> findByUsername(String username);

}
