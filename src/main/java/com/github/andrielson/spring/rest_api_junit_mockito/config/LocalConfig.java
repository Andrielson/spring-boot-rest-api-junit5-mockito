package com.github.andrielson.spring.rest_api_junit_mockito.config;

import com.github.andrielson.spring.rest_api_junit_mockito.domain.User;
import com.github.andrielson.spring.rest_api_junit_mockito.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@RequiredArgsConstructor
@Configuration
@Profile("local")
public class LocalConfig {

    private final UserRepository userRepository;

    @Bean
    public void startDB() {
        var user1 = new User("User One", "user_one@email.com", "password");
        var user2 = new User("User Two", "user_two@email.com", "password");

        userRepository.saveAll(List.of(user1, user2));
    }
}
