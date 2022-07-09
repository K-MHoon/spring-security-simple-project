package com.example.springsecuritysimpleproject.service.user;

import com.example.springsecuritysimpleproject.domain.resources.Resources;
import com.example.springsecuritysimpleproject.dto.resources.ResourcesDto;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ResourcesService {

    Resources getResources(Long id);

    List<Resources> getResources();

    void createResources(ResourcesDto resourcesDto) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    void deleteResources(Long id) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    ResourcesDto getResourcesDtoById(Long id);

    ResourcesDto getCleanRoleResourcesDto();
}
