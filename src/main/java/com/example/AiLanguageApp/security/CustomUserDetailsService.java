package com.example.AiLanguageApp.security;

import com.example.AiLanguageApp.model.User;
import com.example.AiLanguageApp.model.UserRole;
import com.example.AiLanguageApp.Repository.UserRepository;
import com.example.AiLanguageApp.Repository.UserRoleRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public CustomUserDetailsService(
            UserRepository userRepository,
            UserRoleRepository userRoleRepository) {

        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found with email: " + email
                        )
                );

        List<UserRole> userRoles =
                userRoleRepository.findByUser_Id(user.getId());

        String[] roles = userRoles.stream()
                .map(userRole -> userRole.getRole().getName())
                .toArray(String[]::new);

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword_hash())
                .roles(roles)
                .build();
    }
}