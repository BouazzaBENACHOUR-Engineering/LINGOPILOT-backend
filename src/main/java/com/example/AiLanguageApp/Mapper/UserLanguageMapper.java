package com.example.AiLanguageApp.Mapper;

import org.springframework.stereotype.Component;

import com.example.AiLanguageApp.DTO.Response.UserLanguageResponse;
import com.example.AiLanguageApp.model.UserLanguage;

@Component
public class UserLanguageMapper {

    public UserLanguageResponse toResponse(
            UserLanguage userLanguage) {

        if (userLanguage == null) {
            return null;
        }

        UserLanguageResponse response =
                new UserLanguageResponse();

        response.setId(userLanguage.getId());

        if (userLanguage.getUser_id() != null) {
            response.setUser_id(
                    userLanguage.getUser_id().getId()
            );
        }

        if (userLanguage.getLanguage_id() != null) {
            response.setLanguage_id(
                    userLanguage.getLanguage_id().getId()
            );
        }

        if (userLanguage.getLevel_id() != null) {
            response.setLevel_id(
                    userLanguage.getLevel_id().getId()
            );
        }

        response.setIs_primary(
                userLanguage.getIs_primary()
        );

        response.setStarted_at(
                userLanguage.getStarted_at()
        );

        return response;
    }
}