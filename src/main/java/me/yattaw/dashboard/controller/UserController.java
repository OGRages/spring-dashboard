package me.yattaw.dashboard.controller;

import me.yattaw.dashboard.entities.User;
import me.yattaw.dashboard.requests.UserCreateRequest;
import me.yattaw.dashboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserCreateRequest request) {
        Map<String, Object> map = new LinkedHashMap<>();

        if (request.username().length() < 4 || request.username().length() > 16) {
            map.put("status", 0);
            map.put("message", "Username or email is unacceptable.");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

        User user = userService.saveUser(request);
        if (user != null) {
            map.put("status", 1);
            map.put("message", "User has been registered Successfully!");
            map.put("username", request.username());
            map.put("email", request.email());
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        } else {
            map.put("status", 0);
            map.put("message", "User already exists with that username or email!");
            return new ResponseEntity<>(map, HttpStatus.CONFLICT);
        }
    }

}
