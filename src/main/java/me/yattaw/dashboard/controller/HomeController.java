package me.yattaw.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

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

    @GetMapping("/tickets")
    private String tickets() {
        return "tickets";
    }

}
