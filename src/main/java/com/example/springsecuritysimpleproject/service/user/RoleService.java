package com.example.springsecuritysimpleproject.service.user;

import com.example.springsecuritysimpleproject.domain.role.Role;

import java.util.List;

public interface RoleService {

    Role getRole(Long id);

    List<Role> getRoles();

    void createRole(Role role);

    void deleteRole(Long id);
}
