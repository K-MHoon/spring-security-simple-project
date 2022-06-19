package com.example.springsecuritysimpleproject.security.provider;

import com.example.springsecuritysimpleproject.security.context.AccountContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        // 1. 계정 정보 있는지 검증
        AccountContext accountContext = (AccountContext) userDetailsService.loadUserByUsername(username);

        // 2. 패스워드가 매칭되는지 검증
        if(!passwordEncoder.matches(password, accountContext.getAccount().getPassword())) {
            throw new BadCredentialsException("BadCredentialsException");
        }

        // 3. 관련 Token을 생성한다.
        // UsernamePasswordAuthenticationToken 2번째 생성자(setAuthentication = True)에 맞게 생성한다.
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(accountContext.getAccount(),
                null, accountContext.getAuthorities());

        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
