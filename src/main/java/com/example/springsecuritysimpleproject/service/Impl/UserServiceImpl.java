package com.example.springsecuritysimpleproject.service.Impl;

import com.example.springsecuritysimpleproject.domain.account.Account;
import com.example.springsecuritysimpleproject.domain.role.Role;
import com.example.springsecuritysimpleproject.dto.account.AccountDto;
import com.example.springsecuritysimpleproject.repository.role.RoleRepository;
import com.example.springsecuritysimpleproject.repository.user.UserRepository;
import com.example.springsecuritysimpleproject.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service("userService")
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public void createUser(AccountDto accountDto) {
        Account account = getAccountDtoToEntity(accountDto);
        setUserRoles(account);
        userRepository.save(account);
    }

    @Override
    public void modifyUser(AccountDto accountDto) {
        Account account = getAccountDtoToEntity(accountDto);

        if(Objects.nonNull(accountDto.getRoles())) {
            Set<Role> roles = new HashSet<>();
            accountDto.getRoles().forEach(role -> {
                roles.add(findByRoleName(role));
            });
            account.setUserRoles(roles);
        }

        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        userRepository.save(account);
    }

    @Override
    public List<Account> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public AccountDto getUser(Long id) {
        Account account = userRepository.findById(id).orElse(new Account());
        AccountDto accountDto = modelMapper.map(account, AccountDto.class);
        List<String> roles = account.getUserRoles().stream().
                map(role -> role.getRoleName()).
                collect(Collectors.toList());
        accountDto.setRoles(roles);
        return accountDto;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private Account getAccountDtoToEntity(AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return account;
    }

    private void setUserRoles(Account account) {
        Role role = findByRoleName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        account.setUserRoles(roles);
    }

    private Role findByRoleName(String roleName) {
        Role role = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 RoleName이 존재하지 않습니다."));
        return role;
    }
}
