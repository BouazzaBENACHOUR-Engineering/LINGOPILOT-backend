package com.example.AiLanguageApp.Mapper;

import org.springframework.stereotype.Component;

import com.example.AiLanguageApp.DTO.Response.ExerciseResponse;
import com.example.AiLanguageApp.model.Exercise;

@Component
public class ExerciseMapper {

    public ExerciseResponse toResponse(
            Exercise exercise) {

        if (exercise == null) {
            return null;
        }

        ExerciseResponse response =
                new ExerciseResponse();

        response.setId(exercise.getId());

        if (exercise.getLesson_id() != null) {
            response.setLesson_id(
                    exercise.getLesson_id().getId()
            );
        }

        response.setType(exercise.getType());
        response.setQuestion(exercise.getQuestion());
        response.setDifficulty(exercise.getDifficulty());
        response.setPoints(exercise.getPoints());
        response.setSequence(exercise.getSequence());
        response.setCreated_at(exercise.getCreated_at());

        return response;
    }
}