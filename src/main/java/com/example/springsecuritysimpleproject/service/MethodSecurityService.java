package com.example.springsecuritysimpleproject.service;

import com.example.springsecuritysimpleproject.security.interceptor.CustomMethodSecurityInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebApplicationContext;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Component
@RequiredArgsConstructor
public class MethodSecurityService {

    private final MapBasedMethodSecurityMetadataSource mapBasedMethodSecurityMetadataSource;
    private final AnnotationConfigServletWebApplicationContext applicationContext;
    private final CustomMethodSecurityInterceptor methodSecurityInterceptor;

    private Map<String, Object> proxyMap = new HashMap<>();
    private Map<String, ProxyFactory> advisedMap = new HashMap<>();
    private Map<String, Object> targetMap = new HashMap<>();

    public void addMethodSecured(String className, String roleName) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        // 클래스와 메서드에 대한 정보를 리플렉션을 통해 추출
        int lastDotIndex = className.lastIndexOf(".");
        String methodName = className.substring(lastDotIndex + 1);
        String typeName = className.substring(0, lastDotIndex);
        Class<?> type = ClassUtils.resolveClassName(typeName, ClassUtils.getDefaultClassLoader());
        String beanName = type.getSimpleName().substring(0, 1).toLowerCase() + type.getSimpleName().substring(1);

        ProxyFactory proxyFactory = advisedMap.get(beanName);
        Object target = targetMap.get(beanName);

        if(Objects.isNull(proxyFactory)) {
            proxyFactory = new ProxyFactory();
            if(Objects.isNull(target)) {
                proxyFactory.setTarget(type.getDeclaredConstructor().newInstance());
            } else {
                proxyFactory.setTarget(target);
            }
            proxyFactory.addAdvice(methodSecurityInterceptor);
            advisedMap.put(beanName, proxyFactory);
        } else {
            int adviceIndex = proxyFactory.indexOf(methodSecurityInterceptor);
            if(adviceIndex == -1) {
                proxyFactory.addAdvice(methodSecurityInterceptor);
            }
        }

        Object proxy = proxyMap.get(beanName);

        if(Objects.isNull(proxy)) {
            proxy = proxyFactory.getProxy();
            proxyMap.put(beanName, proxy);

            List<ConfigAttribute> attr = Arrays.asList(new SecurityConfig(roleName));
            mapBasedMethodSecurityMetadataSource.addSecureMethod(type, methodName, attr);

            DefaultSingletonBeanRegistry registry = (DefaultSingletonBeanRegistry) applicationContext.getBeanFactory();
            registry.destroySingleton(beanName);
            registry.registerSingleton(beanName, proxy);
        }
    }

    public void removeMethodSecured(String className) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        int lastDotIndex = className.lastIndexOf(".");
        String typeName = className.substring(0, lastDotIndex);
        Class<?> type = ClassUtils.resolveClassName(typeName, ClassUtils.getDefaultClassLoader());
        String beanName = type.getSimpleName().substring(0, 1).toLowerCase() + type.getSimpleName().substring(1);
        Object newInstance = type.getDeclaredConstructor().newInstance();

        DefaultSingletonBeanRegistry registry = (DefaultSingletonBeanRegistry) applicationContext.getBeanFactory();
        ProxyFactory proxyFactory = advisedMap.get(beanName);

        if(Objects.nonNull(proxyFactory)) {
            proxyFactory.removeAdvice(methodSecurityInterceptor);
        } else {
            registry.destroySingleton(beanName);
            registry.registerSingleton(beanName, newInstance);
            targetMap.put(beanName, newInstance);
        }
    }
}
