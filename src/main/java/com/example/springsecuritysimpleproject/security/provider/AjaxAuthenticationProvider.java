package com.example.springsecuritysimpleproject.security.provider;

import com.example.springsecuritysimpleproject.security.context.AccountContext;
import com.example.springsecuritysimpleproject.security.token.AjaxAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class AjaxAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        // 1. 계정 정보 있는지 검증
        AccountContext accountContext = (AccountContext) userDetailsService.loadUserByUsername(username);

        // 2. 패스워드가 매칭되는지 검증
        if (!passwordEncoder.matches(password, accountContext.getAccount().getPassword())) {
            throw new BadCredentialsException("Invalid Password");
        }

        // 3. 관련 Token을 생성한다.
        return new AjaxAuthenticationToken(accountContext.getAccount(),
                null, accountContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AjaxAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
