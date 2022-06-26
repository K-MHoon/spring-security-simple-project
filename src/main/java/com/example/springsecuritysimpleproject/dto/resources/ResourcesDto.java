package com.example.springsecuritysimpleproject.dto.resources;

import com.example.springsecuritysimpleproject.domain.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourcesDto {

    private Long id;
    private String resourceName;
    private String httpMethod;
    private Integer orderNum;
    private String resourceType;
    private String roleName;
    private Set<Role> roleSet;
}
