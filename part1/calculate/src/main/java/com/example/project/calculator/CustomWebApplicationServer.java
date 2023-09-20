package com.example.project.calculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomWebApplicationServer {
    private final int port;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
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
                /**
                 * Step3 - Thread Pool 을 적용해 안정적인 서비스각 가능하도록 실행
                 */
                executorService.execute(new ClientRequestHandler(clientSocket));
            }
        }
    }
}
