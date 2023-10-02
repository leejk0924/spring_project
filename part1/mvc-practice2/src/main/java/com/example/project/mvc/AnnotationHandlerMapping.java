package com.example.project.mvc;

import com.example.project.mvc.annotation.RequestMapping;
import com.example.project.mvc.controller.RequestMethod;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHandlerMapping implements HandlerMapping {

    private final Object[] basePackage;
    private Map<HandlerKey, AnnotationHandler> handlers = new HashMap<>();

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    public void initialize() {
        Reflections reflections = new Reflections(basePackage);
        // HomeController
        // Controller 어노테이션이 붙은 클래스타입 객체를 모두 가져온다.
        Set<Class<?>> clazzWithControllerAnnotation = reflections.getTypesAnnotatedWith(com.example.project.mvc.annotation.Controller.class, true);
        clazzWithControllerAnnotation.forEach(clazz ->
                Arrays.stream(clazz.getDeclaredMethods()).forEach(declaredMethod -> {
                    // 클래스 타입 객체 안에 메서드 중 RequestMapping 어노테이션이 붙은 메서드를 가져온다.
                    RequestMapping requestMappingAnnotation = declaredMethod.getDeclaredAnnotation(RequestMapping.class);
                    // RequestMapping 어노테이션의 정보로 HandlerKey 를 만들어준다.
                    Arrays.stream(getRequestMethod(requestMappingAnnotation))
                            .forEach(requestMethod -> handlers.put(
                                    new HandlerKey(requestMethod, requestMappingAnnotation.value()), new AnnotationHandler(clazz, declaredMethod)
                            ));
                }));
    }

    private RequestMethod[] getRequestMethod(RequestMapping requestMappingAnnotation) {
        return requestMappingAnnotation.method();
    }

    @Override
    public Object findHandler(HandlerKey handlerKey) {
        return handlers.get(handlerKey);
    }
}
