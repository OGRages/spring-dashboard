package me.yattaw.dashboard.service;

import me.yattaw.dashboard.entities.ticket.Ticket;
import me.yattaw.dashboard.repository.TicketRepository;
import me.yattaw.dashboard.requests.ticket.TicketCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class TicketService {

    @Autowired
    public TicketRepository ticketRepository;

    public Ticket saveTicket(TicketCreateRequest request) {
        return ticketRepository.save(
                Ticket.builder()
                        .subject(request.subject())
                        .createdAt(LocalDateTime.now())
                        .build()
        );
    }

}
