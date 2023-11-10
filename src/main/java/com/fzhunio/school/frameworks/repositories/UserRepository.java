package com.fzhunio.school.frameworks.repositories;

import com.fzhunio.school.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
    Optional<UserEntity> getEmail(String email);
}
