package com.example.springsecuritysimpleproject.service.Impl;

import com.example.springsecuritysimpleproject.domain.account.Account;
import com.example.springsecuritysimpleproject.dto.account.AccountDto;
import com.example.springsecuritysimpleproject.repository.user.UserRepository;
import com.example.springsecuritysimpleproject.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void createUser(AccountDto accountDto) {
        userRepository.save(getAccountDtoToEntity(accountDto));
    }

    @Override
    public void modifyUser(AccountDto accountDto) {

    }

    @Override
    public List<Account> getUsers() {
        return null;
    }

    @Override
    public AccountDto getUser(Long id) {
        return null;
    }

    @Override
    public void deleteUser(Long idx) {

    }

    private Account getAccountDtoToEntity(AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return account;
    }
}
