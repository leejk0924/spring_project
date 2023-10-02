package com.example.project.mvc;

public interface HandlerMapping {
    Object findHandler(HandlerKey handlerKey);
}
