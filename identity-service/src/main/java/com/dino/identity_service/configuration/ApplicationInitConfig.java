package com.dino.identity_service.configuration;

import com.dino.identity_service.entity.User;
import com.dino.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByEmail("admin").isEmpty()) {
                User user = User.builder()
                        .email("admin")
                        .password(passwordEncoder.encode("admin123"))
                        .role("ADMIN")
                        .build();
                userRepository.save(user);
                log.warn("admin user has been created with default password: admin123, please change it");
            }
        };
    }


}
