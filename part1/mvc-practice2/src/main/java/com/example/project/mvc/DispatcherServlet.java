package com.example.project.mvc;

import com.example.project.mvc.controller.Controller;
import com.example.project.mvc.controller.RequestMethod;
import com.example.project.mvc.view.JspViewResolver;
import com.example.project.mvc.view.View;
import com.example.project.mvc.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

// 어떠한 경로를 입력하더라도 DispatcherServlet 이 실행되도록 루트를 등록
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    private List<ViewResolver> viewResolvers;
    @Override
    public void init() throws ServletException {
        requestMappingHandlerMapping = new RequestMappingHandlerMapping();
        requestMappingHandlerMapping.init();
        viewResolvers = Collections.singletonList(new JspViewResolver());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("[DispatcherServlet] service started.");

        try {
            Controller handler = requestMappingHandlerMapping.findHandler(new HandlerKey(RequestMethod.valueOf(req.getMethod()), req.getRequestURI()));
            // redirect vs forward
            String viewName = handler.handleRequest(req, resp);

            for (ViewResolver viewResolver : viewResolvers) {
                View view = viewResolver.resolveView(viewName);
                view.render(new HashMap<>(), req, resp);
            }
        } catch (Exception e) {
            log.error("exception occurred: [{}]", e.getMessage(), e);
            throw new ServletException(e);
        }
    }
}
