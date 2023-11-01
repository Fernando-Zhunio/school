package com.fzhunio.school.user.entity;

import com.fzhunio.school.role.RoleEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Entity
@Builder
@Data
@Table(name = "user")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    @Email
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;

}
