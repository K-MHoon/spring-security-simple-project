package com.example.springsecuritysimpleproject.domain.resources;

import com.example.springsecuritysimpleproject.domain.role.Role;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "RESOURCES")
@Data
@ToString(exclude = {"roleSet"})
@EntityListeners(value = {AuditingEntityListener.class})
@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resources implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "resource_id")
    private Long id;

    private String resourceName;

    private String httpMethod;

    private Integer orderNum;

    private String resourceType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_sources",
            joinColumns = {@JoinColumn(name = "resource_id")},
            inverseJoinColumns = { @JoinColumn(name = "role_id")})
    private Set<Role> roleSet = new HashSet<>();
}
