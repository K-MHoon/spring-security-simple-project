package com.example.springsecuritysimpleproject.repository.security;

import com.example.springsecuritysimpleproject.domain.security.AccessIp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessIpRepository extends JpaRepository<AccessIp, Long> {
    Optional<AccessIp> findByIpAddress(String ipAddress);
}
