package com.example.springsecuritysimpleproject.security.init;

import com.example.springsecuritysimpleproject.service.user.RoleHierarchyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityInitializer implements ApplicationRunner {

    private final RoleHierarchyService roleHierarchyService;
    private final RoleHierarchyImpl roleHierarchy;

    // 권한 계층(String)을 넣어줌으로써, 설정된다.
    // Voter에서 심사할 때 로직에 의해서 ADMIN 권한이 하위 권한을 포함한 처리가 이루어 진다.
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String allHierarchy = roleHierarchyService.findAllHierarchy();
        roleHierarchy.setHierarchy(allHierarchy);
    }
}
