package com.example.springsecuritysimpleproject.domain.account;

import com.example.springsecuritysimpleproject.domain.role.Role;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@ToString(exclude = {"userRoles"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Account implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String email;
    private String age;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "account_roles",
            joinColumns = {@JoinColumn(name = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> userRoles = new HashSet<>();
}
