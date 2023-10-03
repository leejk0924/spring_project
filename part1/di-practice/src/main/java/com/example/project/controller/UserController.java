package com.example.project.controller;

import com.example.project.annotation.Controller;
import com.example.project.annotation.Inject;
import com.example.project.service.UserService;

@Controller
public class UserController {
    private final UserService userService;
    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }
}
