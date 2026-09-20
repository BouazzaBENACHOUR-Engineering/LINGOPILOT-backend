package com.example.AiLanguageApp.security;

import com.example.AiLanguageApp.model.User;

public interface AuthenticatedUserService {

    User getAuthenticatedUser();

    Long getAuthenticatedUserId();
}