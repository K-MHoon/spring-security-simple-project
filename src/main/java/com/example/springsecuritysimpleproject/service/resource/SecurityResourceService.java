package com.example.springsecuritysimpleproject.service.resource;

import com.example.springsecuritysimpleproject.domain.resources.Resources;
import com.example.springsecuritysimpleproject.domain.security.AccessIp;
import com.example.springsecuritysimpleproject.repository.resources.ResourcesRepository;
import com.example.springsecuritysimpleproject.repository.security.AccessIpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SecurityResourceService {

    private final ResourcesRepository resourcesRepository;
    private final AccessIpRepository accessIpRepository;

    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getResourceList() {
        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> result = new LinkedHashMap<>();
        List<Resources> resourcesList = resourcesRepository.findAllResources();
        resourcesList.forEach(re -> {
            List<ConfigAttribute> configAttributeList = new ArrayList<>();
            re.getRoleSet().forEach(role -> {
                configAttributeList.add(new SecurityConfig(role.getRoleName()));
            });
            result.put(new AntPathRequestMatcher(re.getResourceName()), configAttributeList);
        });
        return result;
    }

    public LinkedHashMap<String, List<ConfigAttribute>> getMethodResourceList() {
        LinkedHashMap<String, List<ConfigAttribute>> result = new LinkedHashMap<>();
        List<Resources> resourcesList = resourcesRepository.findAllMethodResources();
        resourcesList.forEach(re -> {
            List<ConfigAttribute> configAttributeList = new ArrayList<>();
            re.getRoleSet().forEach(role -> {
                configAttributeList.add(new SecurityConfig(role.getRoleName()));
            });
            result.put(re.getResourceName(), configAttributeList);
        });
        return result;
    }

    public List<String> getAddressIpList() {
        List<String> accessIpList = accessIpRepository.findAll().stream().map(AccessIp::getIpAddress)
                .collect(Collectors.toList());
        return accessIpList;
    }
}
