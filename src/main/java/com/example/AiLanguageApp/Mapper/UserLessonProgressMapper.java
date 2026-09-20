package com.example.AiLanguageApp.Mapper;

import org.springframework.stereotype.Component;

import com.example.AiLanguageApp.DTO.Response.UserLessonProgressResponse;
import com.example.AiLanguageApp.model.UserLessonProgress;

@Component
public class UserLessonProgressMapper {

    public UserLessonProgressResponse toResponse(
            UserLessonProgress userLessonProgress) {

        if (userLessonProgress == null) {
            return null;
        }

        UserLessonProgressResponse response =
                new UserLessonProgressResponse();

        response.setId(userLessonProgress.getId());

        if (userLessonProgress.getUser_id() != null) {
            response.setUser_id(
                    userLessonProgress.getUser_id().getId()
            );
        }

        if (userLessonProgress.getLesson_id() != null) {
            response.setLesson_id(
                    userLessonProgress.getLesson_id().getId()
            );
        }

        response.setStatus(
                userLessonProgress.getStatus()
        );

        response.setCompletion_percentage(
                userLessonProgress.getCompletion_percentage()
        );

        response.setScore(
                userLessonProgress.getScore()
        );

        response.setStarted_at(
                userLessonProgress.getStarted_at()
        );

        response.setCompleted_at(
                userLessonProgress.getCompleted_at()
        );

        return response;
    }
}