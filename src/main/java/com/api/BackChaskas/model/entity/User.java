package com.api.BackChaskas.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(unique = true, length = 50)
    private String username;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String passwordHash;

    private String fullName;
    private String phone;

    private Boolean active = true;

    private LocalDateTime lastLogin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserAddress> addresses = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Favorite> favorites = new HashSet<>();
}