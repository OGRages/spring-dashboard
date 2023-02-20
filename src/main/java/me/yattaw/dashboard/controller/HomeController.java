package me.yattaw.dashboard.controller;

import me.yattaw.dashboard.entities.ticket.Ticket;
import me.yattaw.dashboard.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    private String register() {
        return "register";
    }

    @GetMapping("/home")
    private String home() {
        return "home";
    }

    @GetMapping("/ticket")
    private String ticket() {
        return "ticket";
    }

    @GetMapping("/ticket-view")
    private String ticketView() {
        return "ticket-view";
    }

    @GetMapping("/tickets")
    private String tickets(Model model, Authentication authentication) {
        List<Ticket> tickets = ticketRepository.findByCreatedBy(authentication.getName());
        model.addAttribute("tickets", tickets);
        return "tickets";
    }

}
