package com.example.project.calculator;

import com.example.project.HttpRequest;
import com.example.project.HttpResponse;
import com.example.project.QueryStrings;
import com.example.project.calculator.domain.Calculator;
import com.example.project.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CustomWebApplicationServer {
    private final int port;
    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);
    public CustomWebApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("[CustomWebApplicationServer] started {} port.", port);
            Socket clientSocket;

            logger.info("[CustomWebApplicationServer] waiting for client.");

            // 클라이언트를 기다리고, 연결 시 해당 로직 진입
            while ((clientSocket = serverSocket.accept()) != null) {
                logger.info("[CustomWebApplicationServer] client connected!");

                new Thread(new ClientRequestHandler(clientSocket)).start();
            }
        }
    }
}
