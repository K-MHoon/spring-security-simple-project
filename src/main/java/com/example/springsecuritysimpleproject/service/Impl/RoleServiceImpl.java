package com.example.springsecuritysimpleproject.service.Impl;

import com.example.springsecuritysimpleproject.domain.role.Role;
import com.example.springsecuritysimpleproject.dto.role.RoleDto;
import com.example.springsecuritysimpleproject.repository.role.RoleRepository;
import com.example.springsecuritysimpleproject.service.user.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public Role getRole(Long id) {
        return roleRepository.findById(id).orElse(new Role());
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void createRole(RoleDto roleDto) {
        roleRepository.save(getDtoToEntity(roleDto));
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role getDtoToEntity(RoleDto roleDto) {
        return modelMapper.map(roleDto, Role.class);
    }

    @Override
    public RoleDto getRoleDtoById(Long id) {
        return modelMapper.map(getRole(id), RoleDto.class);
    }
}
