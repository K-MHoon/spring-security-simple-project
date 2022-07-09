package com.example.springsecuritysimpleproject.service.Impl;

import com.example.springsecuritysimpleproject.domain.resources.Resources;
import com.example.springsecuritysimpleproject.domain.role.Role;
import com.example.springsecuritysimpleproject.dto.resources.ResourcesDto;
import com.example.springsecuritysimpleproject.repository.resources.ResourcesRepository;
import com.example.springsecuritysimpleproject.repository.role.RoleRepository;
import com.example.springsecuritysimpleproject.security.metadatasource.UrlFilterInvocationSecurityMetadataSource;
import com.example.springsecuritysimpleproject.service.MethodSecurityService;
import com.example.springsecuritysimpleproject.service.user.ResourcesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ResourcesServiceImpl implements ResourcesService {

    private final ResourcesRepository resourcesRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final UrlFilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;
    private final MethodSecurityService methodSecurityService;

    @Override
    public Resources getResources(Long id) {
        return resourcesRepository.findById(id).orElse(new Resources());
    }

    @Override
    public List<Resources> getResources() {
        return resourcesRepository.findAll(Sort.by(Sort.Order.asc("orderNum")));
    }

    @Override
    public void createResources(ResourcesDto resourcesDto) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Role role = roleRepository.findByRoleName(resourcesDto.getRoleName())
                .orElseThrow(() -> new EntityNotFoundException("Role Entity가 존재하지 않습니다."));

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        Resources resources = modelMapper.map(resourcesDto, Resources.class);
        resources.setRoleSet(roles);

        if("url".equals(resourcesDto.getResourceType())) {
            filterInvocationSecurityMetadataSource.reload();
        } else if("method".equals(resourcesDto.getResourceType())) {
            methodSecurityService.addMethodSecured(resourcesDto.getResourceName(), resourcesDto.getRoleName());
        }

        resourcesRepository.save(resources);
    }

    @Override
    public ResourcesDto getResourcesDtoById(Long id) {
        return modelMapper.map(getResources(id), ResourcesDto.class);
    }

    @Override
    public ResourcesDto getCleanRoleResourcesDto() {
        return ResourcesDto.builder()
                .roleSet(Set.of(new Role()))
                .build();
    }

    @Override
    public void deleteResources(Long id) {
        resourcesRepository.deleteById(id);
    }
}
