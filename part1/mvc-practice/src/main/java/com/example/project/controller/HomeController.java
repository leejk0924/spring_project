package com.example.project.controller;

import com.example.project.annotation.Controller;
import com.example.project.annotation.RequestMapping;
import com.example.project.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(HttpServletRequest request, HttpServletResponse response) {
        return "home";
    }
}
