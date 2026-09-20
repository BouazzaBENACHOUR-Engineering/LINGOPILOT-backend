package com.example.AiLanguageApp.Repository;

import com.example.AiLanguageApp.model.UserNotificationSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserNotificationSettingsRepository
        extends JpaRepository<UserNotificationSettings, Long> {
}