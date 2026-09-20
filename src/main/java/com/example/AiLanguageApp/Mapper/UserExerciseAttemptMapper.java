package com.example.AiLanguageApp.Mapper;

import org.springframework.stereotype.Component;

import com.example.AiLanguageApp.DTO.Response.UserExerciseAttemptResponse;
import com.example.AiLanguageApp.model.UserExerciseAttempt;

@Component
public class UserExerciseAttemptMapper {

    public UserExerciseAttemptResponse toResponse(
            UserExerciseAttempt userExerciseAttempt) {

        if (userExerciseAttempt == null) {
            return null;
        }

        UserExerciseAttemptResponse response =
                new UserExerciseAttemptResponse();

        response.setId(userExerciseAttempt.getId());

        if (userExerciseAttempt.getUser_id() != null) {
            response.setUser_id(
                    userExerciseAttempt.getUser_id().getId()
            );
        }

        if (userExerciseAttempt.getExercise_id() != null) {
            response.setExercise_id(
                    userExerciseAttempt.getExercise_id().getId()
            );
        }

        response.setAnswer(
                userExerciseAttempt.getAnswer()
        );

        response.setIs_correct(
                userExerciseAttempt.getIs_correct()
        );

        response.setScore(
                userExerciseAttempt.getScore()
        );

        response.setAttempted_at(
                userExerciseAttempt.getAttempted_at()
        );

        return response;
    }
}