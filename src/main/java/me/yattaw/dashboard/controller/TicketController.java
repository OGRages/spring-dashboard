package me.yattaw.dashboard.controller;

import me.yattaw.dashboard.entities.ticket.Ticket;
import me.yattaw.dashboard.repository.TicketRepository;
import me.yattaw.dashboard.requests.ticket.TicketCreateRequest;
import me.yattaw.dashboard.service.TicketService;
import me.yattaw.dashboard.userdetails.DashboardUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketRepository ticketRepository;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody TicketCreateRequest request, Authentication authentication) {
        Map<String, Object> map = new LinkedHashMap<>();
        Ticket ticket = ticketService.saveTicket(request, authentication);

        if (ticket != null) {
            map.put("status", 1);
            map.put("message", "Ticket has been created Successfully!");
            map.put("information", request.information());
            map.put("subject", request.subject());
            map.put("created_by", authentication.getName());
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        } else {
            map.put("status", 0);
            map.put("message", "Failed to create ticket.");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody TicketCreateRequest request) {
        Map<String, Object> map = new LinkedHashMap<>();
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

}
