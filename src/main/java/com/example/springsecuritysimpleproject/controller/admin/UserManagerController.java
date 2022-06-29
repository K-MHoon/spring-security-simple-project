package com.example.springsecuritysimpleproject.controller.admin;

import com.example.springsecuritysimpleproject.domain.account.Account;
import com.example.springsecuritysimpleproject.dto.account.AccountDto;
import com.example.springsecuritysimpleproject.service.user.RoleService;
import com.example.springsecuritysimpleproject.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/accounts")
public class UserManagerController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("accounts", userService.getUsers());

        return "/admin/user/list";
    }

    @PostMapping
    public String modifyUser(AccountDto accountDto) {
        userService.modifyUser(accountDto);

        return "redirect:/admin/accounts";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        model.addAttribute("account", userService.getUser(id));
        model.addAttribute("roleList", roleService.getRoles());

        return "admin/user/detail";
    }

    @DeleteMapping("/delete/{id}")
    public String removeUser(@PathVariable Long id, Model model) {
        userService.deleteUser(id);

        return "redirect:/admin/users";
    }
}
