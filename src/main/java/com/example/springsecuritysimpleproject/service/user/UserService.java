package com.example.springsecuritysimpleproject.service.user;

import com.example.springsecuritysimpleproject.dto.account.AccountDto;

public interface UserService {

    void createUser(AccountDto accountDto);
}
