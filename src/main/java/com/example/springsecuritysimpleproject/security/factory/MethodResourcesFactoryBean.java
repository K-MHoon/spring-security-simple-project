package com.example.springsecuritysimpleproject.security.factory;

import com.example.springsecuritysimpleproject.service.resource.SecurityResourceService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public class MethodResourcesFactoryBean implements FactoryBean<LinkedHashMap<String, List<ConfigAttribute>>> {

    private SecurityResourceService securityResourceService;
    private LinkedHashMap<String, List<ConfigAttribute>> resourceMap;


    public void setSecurityResourceService(SecurityResourceService securityResourceService) {
        this.securityResourceService = securityResourceService;
    }

    @Override
    public LinkedHashMap<String, List<ConfigAttribute>> getObject() {

        if(Objects.isNull(resourceMap)) {
            init();
        }

        return resourceMap;
    }

    private void init() {
        resourceMap = securityResourceService.getMethodResourceList();
    }

    @Override
    public Class<?> getObjectType() {
        return LinkedHashMap.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
