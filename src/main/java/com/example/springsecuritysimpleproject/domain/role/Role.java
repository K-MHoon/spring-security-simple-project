package com.example.springsecuritysimpleproject.domain.role;

import com.example.springsecuritysimpleproject.domain.account.Account;
import com.example.springsecuritysimpleproject.domain.resources.Resources;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ROLE")
@Getter
@ToString(exclude = {"users","resourcesSet"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Role implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;

    private String roleName;

    private String roleDesc;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roleSet")
    @OrderBy("orderNum desc")
    private Set<Resources> resourcesSet = new LinkedHashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userRoles")
    private Set<Account> accounts = new HashSet<>();
}
