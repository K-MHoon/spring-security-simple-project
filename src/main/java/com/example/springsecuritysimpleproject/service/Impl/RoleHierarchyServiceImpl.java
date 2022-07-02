package com.example.springsecuritysimpleproject.service.Impl;

import com.example.springsecuritysimpleproject.domain.role.RoleHierarchy;
import com.example.springsecuritysimpleproject.repository.role.RoleHierarchyRepository;
import com.example.springsecuritysimpleproject.service.user.RoleHierarchyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RoleHierarchyServiceImpl implements RoleHierarchyService {

    private final RoleHierarchyRepository roleHierarchyRepository;

    @Transactional
    @Override
    public String findAllHierarchy() {

        List<RoleHierarchy> roleHierarchy = roleHierarchyRepository.findAll();
        StringBuilder concatRoles = new StringBuilder();
        roleHierarchy.stream()
                .filter(r -> Objects.nonNull(r.getParentName()))
                .forEach(r ->
                        concatRoles.append(r.getParentName().getChildName())
                                .append(" > ")
                                .append(r.getChildName())
                                .append("\n")
                );

        return concatRoles.toString();
    }
}
