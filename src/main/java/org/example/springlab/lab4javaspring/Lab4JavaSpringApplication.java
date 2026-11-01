package org.example.springlab.lab4javaspring;

import org.example.springlab.lab4javaspring.entity.AppUser;
import org.example.springlab.lab4javaspring.repository.AppUserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Lab4JavaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab4JavaSpringApplication.class, args);
    }

    @Bean
    @Profile("!test") // Бин НЕ будет запускаться во время тестового профиля
    public CommandLineRunner demoData(AppUserRepo userRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepo.findByUsername("admin").isEmpty()) {
                AppUser user = new AppUser("admin", passwordEncoder.encode("admin123"), "ADMIN");
                userRepo.save(user);
            }
        };
    }

}
