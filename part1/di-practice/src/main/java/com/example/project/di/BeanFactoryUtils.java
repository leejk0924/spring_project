package com.example.project.di;

import com.example.project.annotation.Inject;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.util.Set;

public class BeanFactoryUtils {
    protected static Constructor<?> getInjectedConstructor(Class<?> clazz) {
        // getAllConstructors 메서드를 통해 clazz 타입의 모든 생성자를 가져오는데, 단 Inject 어노테이션이 붙은 생성자만 가져온다.
        Set<Constructor> injectedConstructors = ReflectionUtils.getAllConstructors(clazz, ReflectionUtils.withAnnotation(Inject.class));
        if (injectedConstructors.isEmpty()) {
            return null;
        }
        return injectedConstructors.iterator().next();
    }

}
