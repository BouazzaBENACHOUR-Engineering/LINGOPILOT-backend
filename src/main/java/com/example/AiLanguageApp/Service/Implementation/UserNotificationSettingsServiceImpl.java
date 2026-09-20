package com.example.AiLanguageApp.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.UserNotificationSettingsRequest;
import com.example.AiLanguageApp.DTO.Response.UserNotificationSettingsResponse;
import com.example.AiLanguageApp.Mapper.UserNotificationSettingsMapper;
import com.example.AiLanguageApp.Repository.UserNotificationSettingsRepository;
import com.example.AiLanguageApp.Repository.UserRepository;
import com.example.AiLanguageApp.Service.Interface.UserNotificationSettingsService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.User;
import com.example.AiLanguageApp.model.UserNotificationSettings;

@Service
public class UserNotificationSettingsServiceImpl
        implements UserNotificationSettingsService {

    private final UserNotificationSettingsRepository
            userNotificationSettingsRepository;

    private final UserRepository userRepository;

    private final UserNotificationSettingsMapper
            userNotificationSettingsMapper;

    public UserNotificationSettingsServiceImpl(
            UserNotificationSettingsRepository userNotificationSettingsRepository,
            UserRepository userRepository,
            UserNotificationSettingsMapper userNotificationSettingsMapper) {

        this.userNotificationSettingsRepository =
                userNotificationSettingsRepository;

        this.userRepository =
                userRepository;

        this.userNotificationSettingsMapper =
                userNotificationSettingsMapper;
    }

    @Override
    public UserNotificationSettingsResponse create(
            UserNotificationSettingsRequest request) {

        User user =
                userRepository
                        .findById(request.getUser_id())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User does not exist"
                                )
                        );

        UserNotificationSettings userNotificationSettings =
                new UserNotificationSettings();

        userNotificationSettings.setUser(user);

        userNotificationSettings.setDaily_reminder(
                request.getDaily_reminder()
        );

        userNotificationSettings.setWeekly_report(
                request.getWeekly_report()
        );

        userNotificationSettings.setReview_reminder(
                request.getReview_reminder()
        );

        userNotificationSettings.setAi_notifications(
                request.getAi_notifications()
        );

        UserNotificationSettings savedUserNotificationSettings =
                userNotificationSettingsRepository.save(
                        userNotificationSettings
                );

        return userNotificationSettingsMapper.toResponse(
                savedUserNotificationSettings
        );
    }

    @Override
    public UserNotificationSettings save(
            UserNotificationSettings userNotificationSettings) {

        return userNotificationSettingsRepository.save(
                userNotificationSettings
        );
    }

    @Override
    public UserNotificationSettings update(
            UserNotificationSettings userNotificationSettings) {

        if (userNotificationSettings.getId() == null ||
                !userNotificationSettingsRepository.existsById(
                        userNotificationSettings.getId())) {

            throw new ResourceNotFoundException(
                    "User notification settings do not exist"
            );
        }

        return userNotificationSettingsRepository.save(
                userNotificationSettings
        );
    }

    @Override
    public Optional<UserNotificationSettings> findById(
            Long id) {

        UserNotificationSettings userNotificationSettings =
                userNotificationSettingsRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User notification settings do not exist"
                                )
                        );

        return Optional.of(
                userNotificationSettings
        );
    }

    @Override
    public List<UserNotificationSettings> findAll() {

        return userNotificationSettingsRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        UserNotificationSettings userNotificationSettings =
                userNotificationSettingsRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User notification settings do not exist"
                                )
                        );

        userNotificationSettingsRepository.delete(
                userNotificationSettings
        );
    }
}