package com.example.springsecuritysimpleproject.service.user;

import com.example.springsecuritysimpleproject.domain.resources.Resources;
import com.example.springsecuritysimpleproject.dto.resources.ResourcesDto;

import java.util.List;

public interface ResourcesService {

    Resources getResources(Long id);

    List<Resources> getResources();

    void createResources(ResourcesDto resourcesDto);

    void deleteResources(Long id);

    ResourcesDto getResourcesDtoById(Long id);

    ResourcesDto getCleanRoleResourcesDto();
}
