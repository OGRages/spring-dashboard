package me.yattaw.dashboard.controller;

import me.yattaw.dashboard.requests.UserCreateRequest;
import me.yattaw.dashboard.response.UserCreateResponse;
import me.yattaw.dashboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public UserCreateResponse createUser(@RequestBody UserCreateRequest request) {
        userService.saveUser(request);
        return new UserCreateResponse(request.email(), request.username());
    }

}
