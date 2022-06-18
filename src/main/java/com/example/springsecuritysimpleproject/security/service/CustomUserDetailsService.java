package com.example.springsecuritysimpleproject.security.service;

import com.example.springsecuritysimpleproject.domain.account.Account;
import com.example.springsecuritysimpleproject.repository.user.UserRepository;
import com.example.springsecuritysimpleproject.security.context.AccountContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("해당 계정이 존재하지 않습니다."));

        return new AccountContext(account, Arrays.asList(new SimpleGrantedAuthority(account.getRole())));
    }
}
