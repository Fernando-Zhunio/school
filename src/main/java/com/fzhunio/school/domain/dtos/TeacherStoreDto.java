package com.fzhunio.school.domain.dtos;

import com.fzhunio.school.tools.TypeDoc;
import com.fzhunio.school.tools.TypeSex;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class TeacherStoreDto {
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
    private TypeDoc typeDoc;
    @NotBlank
    private String NumberDoc;
    @NotBlank
    private String Address;
    @NotBlank
    private Date birthdate;
}
