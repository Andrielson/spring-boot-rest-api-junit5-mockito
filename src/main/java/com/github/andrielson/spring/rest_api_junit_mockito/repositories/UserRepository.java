package com.github.andrielson.spring.rest_api_junit_mockito.repositories;

import com.github.andrielson.spring.rest_api_junit_mockito.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
