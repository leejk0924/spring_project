package com.example.project;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequest {
    private final RequestLine requestLine;
//    private final HttpHeaders headers;
//    private final Body body;

    public HttpRequest(BufferedReader br) throws IOException {
        this.requestLine = new RequestLine(br.readLine());
    }
    public boolean isGetRequest() {
        return requestLine.isGetRequest();
    }
    public QueryStrings getQueryString() {
        return requestLine.getQueryStrings();
    }
    public boolean matchPath(String path) {
        return requestLine.matchPath(path);
    }
}
