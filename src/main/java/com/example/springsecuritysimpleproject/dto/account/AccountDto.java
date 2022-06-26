package com.example.springsecuritysimpleproject.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private String username;
    private String password;
    private String email;
    private String age;
    private List<String> roles;
}
