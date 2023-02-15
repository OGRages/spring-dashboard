package me.yattaw.dashboard.service;

import me.yattaw.dashboard.entities.ticket.Ticket;
import me.yattaw.dashboard.entities.ticket.TicketResponse;
import me.yattaw.dashboard.repository.TicketRepository;
import me.yattaw.dashboard.requests.ticket.TicketCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class TicketService {

    @Autowired
    public TicketRepository ticketRepository;

    public Ticket saveTicket(TicketCreateRequest request, Authentication authentication) {

        Ticket ticket = Ticket.builder()
                .subject(request.subject())
                .createdAt(LocalDateTime.now())
                .createdBy(authentication.getName())
                .build();

        TicketResponse firstResponse = TicketResponse.builder()
                .text(request.information())
                .createdAt(LocalDateTime.now())
                .createdBy(authentication.getName())
                .ticket(ticket)
                .build();

        ticket.setResponses(Arrays.asList(firstResponse));

        return ticketRepository.save(ticket);
    }

}
