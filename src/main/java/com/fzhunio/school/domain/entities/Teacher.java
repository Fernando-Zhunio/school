package com.fzhunio.school.domain.entities;

import com.fzhunio.school.tools.TypeDoc;
import com.fzhunio.school.tools.TypeSex;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Entity
@Builder
@Data
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String phone;
    @NotBlank
    private TypeSex sex;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private TypeDoc typeDoc;
    @NotBlank
    private String numberDoc;
    @NotBlank
    private String address;
    @NotBlank
    private Date birthday;
}
