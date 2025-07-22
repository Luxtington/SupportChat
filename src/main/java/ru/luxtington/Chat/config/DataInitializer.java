package ru.luxtington.Chat.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.luxtington.Chat.model.Role;
import ru.luxtington.Chat.model.User;
import ru.luxtington.Chat.repository.RoleRepository;
import ru.luxtington.Chat.repository.UserRepository;

@Slf4j
@Component
public class DataInitializer {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init(){
        if (!roleRepository.findByName("ROLE_USER").isPresent()){
            Role roleUser = new Role("ROLE_USER");
            roleRepository.save(roleUser);
            log.info("role of user was uploaded to db");
        }

        if (!roleRepository.findByName("ROLE_MANAGER").isPresent()){
            Role roleUser = new Role("ROLE_MANAGER");
            roleRepository.save(roleUser);
            log.info("role of manager was uploaded to db");
        }

        if (!roleRepository.findByName("ROLE_ADMIN").isPresent()){
            Role roleUser = new Role("ROLE_ADMIN");
            roleRepository.save(roleUser);
            log.info("role of admin was uploaded to db");
        }

        if (userRepository.findByRoleName("ROLE_ADMIN").isEmpty()){
            User admin = new User("admin", "admin", "admin", 1950, "admin1", passwordEncoder.encode("admin1"));
            admin.addRoleToUser(roleRepository.findByName("ROLE_ADMIN").orElse(null));
            userRepository.save(admin);
            log.info("first admin was created");
        }
    }
}
