package com.example.springsecuritysimpleproject.controller.admin;

import com.example.springsecuritysimpleproject.dto.role.RoleDto;
import com.example.springsecuritysimpleproject.service.user.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public String getRoles(Model model) {
        model.addAttribute("roles", roleService.getRoles());

        return "admin/role/list";
    }

    @GetMapping("/register")
    public String viewRoles(Model model) {
        model.addAttribute("role", new RoleDto());

        return "admin/role/detail";
    }

    @PostMapping
    public String createRole(RoleDto roleDto) {
        roleService.createRole(roleDto);

        return "redirect:/admin/roles";
    }

    @GetMapping("/{id}")
    public String getRole(@PathVariable Long id, Model model) {
        model.addAttribute("role", roleService.getRoleDtoById(id));

        return "admin/role/detail";
    }

    @DeleteMapping("/delete/{id}")
    public String removeRole(@PathVariable Long id, Model model) {
        roleService.deleteRole(id);

        return "redirect:/admin/roles";
    }
}
