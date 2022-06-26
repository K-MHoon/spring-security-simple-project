package com.example.springsecuritysimpleproject.service.user;

import com.example.springsecuritysimpleproject.domain.account.Account;
import com.example.springsecuritysimpleproject.dto.account.AccountDto;

import java.util.List;

public interface UserService {

    void createUser(AccountDto accountDto);

    void modifyUser(AccountDto accountDto);

    List<Account> getUsers();

    AccountDto getUser(Long id);

    void deleteUser(Long idx);
}
