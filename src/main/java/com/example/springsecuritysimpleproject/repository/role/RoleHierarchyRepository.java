package com.example.springsecuritysimpleproject.repository.role;

import com.example.springsecuritysimpleproject.domain.role.RoleHierarchy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleHierarchyRepository extends JpaRepository<RoleHierarchy, Long> {

    Optional<RoleHierarchy> findByChildName(String roleName);
}
