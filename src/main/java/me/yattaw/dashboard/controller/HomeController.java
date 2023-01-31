package me.yattaw.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String login(Principal principal) {
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

}
