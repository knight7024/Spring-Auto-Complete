package com.example.autocomplete.user.repository;

import com.example.autocomplete.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityJPARepository extends JpaRepository<UserEntity, Long> {
}
