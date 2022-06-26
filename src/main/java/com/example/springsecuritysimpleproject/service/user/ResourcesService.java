package com.example.springsecuritysimpleproject.service.user;

import com.example.springsecuritysimpleproject.domain.resources.Resources;

import java.util.List;

public interface ResourcesService {

    Resources getResources(Long id);

    List<Resources> getResources();

    void createResources(Resources resources);

    void deleteResources(Long id);
}
