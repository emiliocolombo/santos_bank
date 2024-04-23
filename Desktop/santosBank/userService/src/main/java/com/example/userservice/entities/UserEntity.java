package com.example.userservice.entities;

import jakarta.persistence.*;
import lombok.*;


import java.util.HashSet;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable=false)
    String name;
    @Column(nullable=false)
    String password;
    @Column(nullable=false)
    String surname;
    @Column(nullable=false)
    String email;
    @Column(nullable=false)
    Long phone;
    @Column(nullable=false)
    Date bithdate;

    @Column(name = "is_enabled")
    private boolean isEnabled;
    @Column(name = "account_no_expired")
    private boolean accountNoExpired;
    @Column(name = "account_no_locked")
    private boolean accountNoLocked;
    @Column(name = "creedentials_no_expired")
    private boolean creedentialsNoExpired;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))//configuramos las claves foraneas
    //un set es como una lista pero que no permite repetidos
    private Set<RoleEntity> roles = new HashSet<>();


}
