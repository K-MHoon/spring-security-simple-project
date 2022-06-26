package com.example.springsecuritysimpleproject.repository.role;

import com.example.springsecuritysimpleproject.domain.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
