package com.example.springsecuritysimpleproject.repository.resources;

import com.example.springsecuritysimpleproject.domain.resources.Resources;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourcesRepository extends JpaRepository<Resources, Long> {
}
