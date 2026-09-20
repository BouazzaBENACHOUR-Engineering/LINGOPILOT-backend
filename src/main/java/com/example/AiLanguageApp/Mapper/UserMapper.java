package com.example.AiLanguageApp.Mapper;

import org.springframework.stereotype.Component;

import com.example.AiLanguageApp.DTO.Response.UserResponse;
import com.example.AiLanguageApp.model.User;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {

        if (user == null) {
            return null;
        }

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirst_name(user.getFirst_name());
        response.setLast_name(user.getLast_name());

        if (user.getNative_language_id() != null) {
            response.setNative_language_id(
                    user.getNative_language_id().getId()
            );
        }

        response.setStatus(user.getStatus());
        response.setCreated_at(user.getCreated_at());
        response.setUpdated_at(user.getUpdated_at());

        return response;
    }
}