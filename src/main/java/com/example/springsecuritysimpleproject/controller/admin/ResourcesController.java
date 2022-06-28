package com.example.springsecuritysimpleproject.controller.admin;

import com.example.springsecuritysimpleproject.dto.resources.ResourcesDto;
import com.example.springsecuritysimpleproject.service.user.ResourcesService;
import com.example.springsecuritysimpleproject.service.user.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/resources")
public class ResourcesController {

    private final ResourcesService resourcesService;
    private final RoleService roleService;

    @GetMapping
    public String getResources(Model model) {
        model.addAttribute("resources", resourcesService.getResources());

        return "/admin/resource/list";
    }

    @PostMapping
    public String createResources(ResourcesDto resourcesDto) {
        resourcesService.createResources(resourcesDto);

        return "redirect:/admin/resources";
    }

    @GetMapping("/{id}")
    public String getResources(@PathVariable Long id, Model model) {
        model.addAttribute("roleList", roleService.getRoles());
        model.addAttribute("resources", resourcesService.getResourcesDtoById(id));

        return "admin/resources/detail";
    }

    @GetMapping("/register")
    public String viewRoles(Model model) {
        model.addAttribute("roleList", roleService.getRoles());
        model.addAttribute("resources", resourcesService.getCleanRoleResourcesDto());

        return "admin/resource/detail";
    }

    @DeleteMapping("/delete/{id}")
    public String removeResources(@PathVariable Long id, Model model) {
        resourcesService.deleteResources(id);

        return "redirect:/admin/resources";
    }
}
