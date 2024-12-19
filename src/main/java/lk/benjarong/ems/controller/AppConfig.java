package lk.benjarong.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import lk.benjarong.ems.entity.UserEntity;
import lk.benjarong.ems.entity.UserRole;
import lk.benjarong.ems.repository.UserRepository;
import lk.benjarong.ems.service.UserService;

@Configuration
public class AppConfig {
    @Autowired
    PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserService userService;

    public AppConfig(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Bean
    public CommandLineRunner createSuperAdmin() {
        return args -> {
            // Check if there is already a superadmin in the database
            if (!userRepository.existsByUsername("superadmin")) {
                UserEntity superAdmin = new UserEntity();
                superAdmin.setUsername("superadmin");
                superAdmin.setEmail("siamcolombo@gmail.com");
                superAdmin.setPassword(passwordEncoder.encode("superadmin"));  // Encrypt the password
                superAdmin.setFullname("Super Admin");
                superAdmin.setRole(UserRole.SUPERADMIN);

                // Save the default superadmin
                userRepository.save(superAdmin);
                System.out.println("Default SuperAdmin created!");
            } else {
                System.out.println("SuperAdmin already exists, not creating again.");
            }
        };
    }

    @Bean
    public CommandLineRunner createAdmin() {
        return args -> {
            // Check if there is already a superadmin in the database
            if (!userRepository.existsByUsername("admin")) {
                UserEntity admin = new UserEntity();
                admin.setUsername("admin");
                admin.setEmail("siamcolombo@gmail.com");
                admin.setPassword(passwordEncoder.encode("admin"));  // Encrypt the password
                admin.setFullname("Admin");
                admin.setRole(UserRole.ADMIN);

                // Save the default superadmin
                userRepository.save(admin);
                System.out.println("Default Admin created!");
            } else {
                System.out.println("Admin already exists, not creating again.");
            }
        };
    }

}
