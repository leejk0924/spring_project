package com.example.project;

import com.example.project.annotation.Controller;
import com.example.project.annotation.Service;
import com.example.project.model.User;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Controller 애노테이션이 설정되어있는 모든 클래스 스캔하여 출력
 */
public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);
    @Test
    void controllerScan() {
        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class, Service.class));
        logger.debug("beans: [{}]", beans);
    }

    @Test
    void showClass() {
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());
        logger.debug("User all declared fields: [{}]", Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()));
        logger.debug("User all declared constructor: [{}]", Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList()));
        logger.debug("User all declared methods: [{}]", Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList()));
    }

    @Test
    void load() throws ClassNotFoundException {
        // 힙 영역에 로드뒈어있는 class 타입의 객체 가져오는 방법1
        Class<User> clazz = User.class;

        // 힙 영역에 로드뒈어있는 class 타입의 객체 가져오는 방법2
        User user = new User("jk", "lee");
        Class<? extends User> clazz2 = user.getClass();

        // 힙 영역에 로드뒈어있는 class 타입의 객체 가져오는 방법3
        Class<?> clazz3 = Class.forName("com.example.project.model.User");

        logger.debug("clazz: [{}]", clazz);
        logger.debug("clazz: [{}]", clazz2);
        logger.debug("clazz: [{}]", clazz3);
        assertThat(clazz == clazz2).isTrue();
        assertThat(clazz == clazz3).isTrue();
        assertThat(clazz2 == clazz3).isTrue();
    }

    private static Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
        Reflections reflections = new Reflections("com.example.project");
        Set<Class<?>> beans = new HashSet<>();

        annotations.forEach(annotation->beans.addAll(reflections.getTypesAnnotatedWith(annotation)));
        // 해당 패키지(com.example.project) 밑에 아래의 어노테이션(Controller.class)가 있을 경우 모두 담는다.
//        beans.addAll(reflections.getTypesAnnotatedWith(Controller.class));
//        beans.addAll(reflections.getTypesAnnotatedWith(Service.class));
        return beans;
    }
}
