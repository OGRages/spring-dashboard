package me.yattaw.dashboard;

import jakarta.transaction.Transactional;
import me.yattaw.dashboard.entities.Role;
import me.yattaw.dashboard.entities.RoleTypes;
import me.yattaw.dashboard.entities.User;
import me.yattaw.dashboard.repository.RoleRepository;
import me.yattaw.dashboard.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class SpringDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDashboardApplication.class, args);
    }

    @Bean
    public CommandLineRunner createDefaults(
            JdbcTemplate jdbcTemplate,
            BCryptPasswordEncoder encoder,
            RoleRepository roleRepository,
            UserRepository userRepository
    ) {
        return (args) -> {
            int rowCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
            if (rowCount == 0) { // always make sure there is at least 1 user added to the database or create an admin account.

                for (RoleTypes roleType : RoleTypes.values()) {
                    createRoleIfNotFound(roleRepository, roleType.getAuthority());
                }

                Role admin = roleRepository.getRoleByName(RoleTypes.ADMIN.getAuthority());

                // Create an admin user on first start up
                userRepository.save(
                        User.builder()
                                .username("admin")
                                .password(encoder.encode("admin"))
                                .roles(Set.of(admin)).build()
                );
            }
        };
    }

    @Transactional
    Role createRoleIfNotFound(RoleRepository roleRepository, String name) {
        Role role = roleRepository.getRoleByName(name);
        if (role == null) {
            roleRepository.save(Role.builder().name(name).build());
        }
        return role;
    }


}
