package com.fzhunio.school.authentication.dto;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Email
    @NotBlank
    private String email;
}

