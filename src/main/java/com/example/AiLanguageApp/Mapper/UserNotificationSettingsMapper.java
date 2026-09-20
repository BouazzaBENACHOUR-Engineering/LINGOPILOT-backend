package com.example.AiLanguageApp.Mapper;

import org.springframework.stereotype.Component;

import com.example.AiLanguageApp.DTO.Response.UserNotificationSettingsResponse;
import com.example.AiLanguageApp.model.UserNotificationSettings;

@Component
public class UserNotificationSettingsMapper {

    public UserNotificationSettingsResponse toResponse(
            UserNotificationSettings userNotificationSettings) {

        if (userNotificationSettings == null) {
            return null;
        }

        UserNotificationSettingsResponse response =
                new UserNotificationSettingsResponse();

        response.setId(
                userNotificationSettings.getId()
        );

        if (userNotificationSettings.getUser() != null) {
            response.setUser_id(
                    userNotificationSettings.getUser().getId()
            );
        }

        response.setDaily_reminder(
                userNotificationSettings.getDaily_reminder()
        );

        response.setWeekly_report(
                userNotificationSettings.getWeekly_report()
        );

        response.setReview_reminder(
                userNotificationSettings.getReview_reminder()
        );

        response.setAi_notifications(
                userNotificationSettings.getAi_notifications()
        );

        return response;
    }
}