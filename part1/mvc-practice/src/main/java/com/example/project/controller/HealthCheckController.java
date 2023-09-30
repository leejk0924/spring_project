package com.example.project.controller;

import com.example.project.annotation.Controller;
import com.example.project.annotation.RequestMapping;
import com.example.project.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HealthCheckController {
    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public String home(HttpServletRequest request, HttpServletResponse response) {
        return "ok";
    }
}
