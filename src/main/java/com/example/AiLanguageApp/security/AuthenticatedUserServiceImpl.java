package com.example.AiLanguageApp.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.Repository.UserRepository;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.User;

@Service
public class AuthenticatedUserServiceImpl
        implements AuthenticatedUserService {

    private final UserRepository userRepository;

    public AuthenticatedUserServiceImpl(
            UserRepository userRepository) {

        this.userRepository =
                userRepository;
    }

    @Override
    public User getAuthenticatedUser() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {

            throw new IllegalStateException(
                    "Authenticated user is required"
            );
        }

        String email =
                authentication.getName();

        return userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Authenticated user does not exist"
                        )
                );
    }

    @Override
    public Long getAuthenticatedUserId() {

        return getAuthenticatedUser()
                .getId();
    }
}