package me.yattaw.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/login")
    private String login() {
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
