package com.example.springsecuritysimpleproject.service.user;

import com.example.springsecuritysimpleproject.domain.role.Role;
import com.example.springsecuritysimpleproject.dto.role.RoleDto;

import java.util.List;

public interface RoleService {

    Role getRole(Long id);

    List<Role> getRoles();

    void createRole(RoleDto roleDto);

    void deleteRole(Long id);

    Role getDtoToEntity(RoleDto roleDto);

    RoleDto getRoleDtoById(Long id);
}
