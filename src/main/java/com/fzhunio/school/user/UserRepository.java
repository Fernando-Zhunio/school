package com.fzhunio.school.user;

import com.fzhunio.school.user.entity.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
        Optional<UserEntity> getEmail(String email);
}
