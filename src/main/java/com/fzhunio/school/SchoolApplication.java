package com.fzhunio.school;

import com.fzhunio.school.frameworks.repositories.UserRepository;
import com.fzhunio.school.role.RoleEntity;
import com.fzhunio.school.role.RoleEnum;
import com.fzhunio.school.domain.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
//
import java.util.Set;

@SpringBootApplication
public class SchoolApplication {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    public static void main(String[] args) {
        SpringApplication.run(SchoolApplication.class, args);
    }

    @Bean
    CommandLineRunner init() {
        return args -> {
            UserEntity user = UserEntity.builder()
                    .email("fzhunio@novicompu.com")
                    .username("fernando")
                    .password(passwordEncoder.encode("fernando1991"))
                    .roles(Set.of(RoleEntity.builder()
                            .name(RoleEnum.valueOf(RoleEnum.ADMIN.name()))
                            .build()))
                    .build();

            userRepository.save(user);
        };
    }

}
