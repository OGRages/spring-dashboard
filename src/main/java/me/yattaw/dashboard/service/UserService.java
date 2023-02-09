package me.yattaw.dashboard.service;

import me.yattaw.dashboard.entities.RoleTypes;
import me.yattaw.dashboard.entities.User;
import me.yattaw.dashboard.repository.RoleRepository;
import me.yattaw.dashboard.repository.UserRepository;
import me.yattaw.dashboard.requests.UserCreateRequest;
import me.yattaw.dashboard.userdetails.DashboardUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.Set;

public class UserService implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);

        if (user == null || !user.isPresent()) {
            throw new UsernameNotFoundException("Could not find user: " + username);
        }

        return new DashboardUserDetails(user.get());
    }

    public User saveUser(UserCreateRequest userRequest) {
        if (userRepository.findByUsername(userRequest.username()).isPresent()) {
            return null;
        } else {
            return userRepository.save(
                    User.builder()
                            .email(userRequest.email())
                            .username(userRequest.username())
                            .password(passwordEncoder.encode(userRequest.password()))
                            .roles(Set.of(roleRepository.getRoleByName(RoleTypes.USER.getAuthority())))
                            .build()
            );
        }
    }


}
