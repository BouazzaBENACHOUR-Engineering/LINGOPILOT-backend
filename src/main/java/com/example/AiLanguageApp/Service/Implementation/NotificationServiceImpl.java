package com.example.AiLanguageApp.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.NotificationRequest;
import com.example.AiLanguageApp.DTO.Response.NotificationResponse;
import com.example.AiLanguageApp.Repository.NotificationRepository;
import com.example.AiLanguageApp.Service.Interface.NotificationService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.Notification;

@Service
public class NotificationServiceImpl
        implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(
            NotificationRepository notificationRepository) {

        this.notificationRepository = notificationRepository;
    }

    @Override
    public NotificationResponse create(
            NotificationRequest request) {

        Notification notification = new Notification();

        Notification savedNotification =
                notificationRepository.save(notification);

        NotificationResponse response =
                new NotificationResponse();

        response.setId(savedNotification.getId());

        return response;
    }

    @Override
    public Notification save(
            Notification notification) {

        return notificationRepository.save(notification);
    }

    @Override
    public Notification update(
            Notification notification) {

        if (notification.getId() == null ||
                !notificationRepository.existsById(
                        notification.getId())) {

            throw new ResourceNotFoundException(
                    "Notification does not exist"
            );
        }

        return notificationRepository.save(notification);
    }

    @Override
    public Optional<Notification> findById(Long id) {

        Notification notification =
                notificationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Notification does not exist"
                                )
                        );

        return Optional.of(notification);
    }

    @Override
    public List<Notification> findAll() {

        return notificationRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        Notification notification =
                notificationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Notification does not exist"
                                )
                        );

        notificationRepository.delete(notification);
    }
}