package com.example.springsecuritysimpleproject.domain.security;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ACCESS_IP")
@Data
@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessIp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IP_ADDRESS", nullable = false)
    private String ipAddress;
}
