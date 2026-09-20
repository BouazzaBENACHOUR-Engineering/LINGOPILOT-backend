package com.example.AiLanguageApp.Service.Implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.ExerciseRequest;
import com.example.AiLanguageApp.DTO.Response.ExerciseResponse;
import com.example.AiLanguageApp.Mapper.ExerciseMapper;
import com.example.AiLanguageApp.Repository.ExerciseRepository;
import com.example.AiLanguageApp.Repository.LessonRepository;
import com.example.AiLanguageApp.Service.Interface.ExerciseService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.Exercise;
import com.example.AiLanguageApp.model.Lesson;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final LessonRepository lessonRepository;
    private final ExerciseMapper exerciseMapper;

    public ExerciseServiceImpl(
            ExerciseRepository exerciseRepository,
            LessonRepository lessonRepository,
            ExerciseMapper exerciseMapper) {

        this.exerciseRepository = exerciseRepository;
        this.lessonRepository = lessonRepository;
        this.exerciseMapper = exerciseMapper;
    }

    @Override
    public ExerciseResponse create(
            ExerciseRequest request) {

        Lesson lesson =
                lessonRepository
                        .findById(request.getLesson_id())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Lesson does not exist"
                                )
                        );

        Exercise exercise = new Exercise();

        exercise.setLesson_id(lesson);
        exercise.setType(request.getType());
        exercise.setQuestion(request.getQuestion());
        exercise.setDifficulty(request.getDifficulty());
        exercise.setPoints(request.getPoints());
        exercise.setSequence(request.getSequence());
        exercise.setCreated_at(LocalDateTime.now());

        Exercise savedExercise =
                exerciseRepository.save(exercise);

        return exerciseMapper.toResponse(savedExercise);
    }

    @Override
    public Exercise save(
            Exercise exercise) {

        return exerciseRepository.save(exercise);
    }

    @Override
    public Exercise update(
            Exercise exercise) {

        if (exercise.getId() == null ||
                !exerciseRepository.existsById(
                        exercise.getId())) {

            throw new ResourceNotFoundException(
                    "Exercise does not exist"
            );
        }

        return exerciseRepository.save(exercise);
    }

    @Override
    public Optional<Exercise> findById(Long id) {

        Exercise exercise =
                exerciseRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Exercise does not exist"
                                )
                        );

        return Optional.of(exercise);
    }

    @Override
    public List<Exercise> findAll() {

        return exerciseRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        Exercise exercise =
                exerciseRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Exercise does not exist"
                                )
                        );

        exerciseRepository.delete(exercise);
    }
}