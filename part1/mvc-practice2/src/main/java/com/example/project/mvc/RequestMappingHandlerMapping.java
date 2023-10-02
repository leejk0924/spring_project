package com.example.project.mvc;

import com.example.project.mvc.controller.Controller;
import com.example.project.mvc.controller.HomeController;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {
    // Key : [url path] , value : [user controller]
    private Map<String, Controller> mappings = new HashMap<>();
    void init() {
        mappings.put("/", new HomeController());
    }

    public Controller findHandler(String uriPath) {
        return mappings.get(uriPath);
    }
}
