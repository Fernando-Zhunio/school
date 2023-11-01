package com.fzhunio.school.authentication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Email
    @NotBlank
    private String email;
}
