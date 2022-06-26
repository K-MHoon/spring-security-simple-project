package com.example.springsecuritysimpleproject.repository.role;

import com.example.springsecuritysimpleproject.domain.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String role_user);
}
