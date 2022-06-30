package com.example.springsecuritysimpleproject.security.service;

import com.example.springsecuritysimpleproject.domain.account.Account;
import com.example.springsecuritysimpleproject.repository.user.UserRepository;
import com.example.springsecuritysimpleproject.security.context.AccountContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("해당 계정이 존재하지 않습니다."));

        List<GrantedAuthority> collect = account.getUserRoles()
                .stream()
                .map(userRole -> userRole.getRoleName())
                .collect(Collectors.toSet())
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new AccountContext(account, collect);
    }
}
