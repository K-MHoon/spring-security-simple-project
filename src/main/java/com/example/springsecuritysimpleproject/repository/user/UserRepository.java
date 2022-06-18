package com.example.springsecuritysimpleproject.repository.user;

import com.example.springsecuritysimpleproject.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Account, Long> {
}
