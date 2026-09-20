package com.example.AiLanguageApp.Service.Implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.UserExerciseAttemptRequest;
import com.example.AiLanguageApp.DTO.Response.UserExerciseAttemptResponse;
import com.example.AiLanguageApp.Mapper.UserExerciseAttemptMapper;
import com.example.AiLanguageApp.Repository.ExerciseRepository;
import com.example.AiLanguageApp.Repository.UserExerciseAttemptRepository;
import com.example.AiLanguageApp.Repository.UserRepository;
import com.example.AiLanguageApp.Service.Interface.UserExerciseAttemptService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.Exercise;
import com.example.AiLanguageApp.model.User;
import com.example.AiLanguageApp.model.UserExerciseAttempt;

@Service
public class UserExerciseAttemptServiceImpl
        implements UserExerciseAttemptService {

    private final UserExerciseAttemptRepository
            userExerciseAttemptRepository;

    private final UserRepository userRepository;

    private final ExerciseRepository exerciseRepository;

    private final UserExerciseAttemptMapper
            userExerciseAttemptMapper;

    public UserExerciseAttemptServiceImpl(
            UserExerciseAttemptRepository userExerciseAttemptRepository,
            UserRepository userRepository,
            ExerciseRepository exerciseRepository,
            UserExerciseAttemptMapper userExerciseAttemptMapper) {

        this.userExerciseAttemptRepository =
                userExerciseAttemptRepository;

        this.userRepository = userRepository;

        this.exerciseRepository = exerciseRepository;

        this.userExerciseAttemptMapper =
                userExerciseAttemptMapper;
    }

    @Override
    public UserExerciseAttemptResponse create(
            UserExerciseAttemptRequest request) {

        User user =
                userRepository
                        .findById(request.getUser_id())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User does not exist"
                                )
                        );

        Exercise exercise =
                exerciseRepository
                        .findById(request.getExercise_id())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Exercise does not exist"
                                )
                        );

        UserExerciseAttempt userExerciseAttempt =
                new UserExerciseAttempt();

        userExerciseAttempt.setUser_id(user);
        userExerciseAttempt.setExercise_id(exercise);
        userExerciseAttempt.setAnswer(request.getAnswer());
        userExerciseAttempt.setAttempted_at(
                LocalDateTime.now()
        );

        UserExerciseAttempt savedUserExerciseAttempt =
                userExerciseAttemptRepository.save(
                        userExerciseAttempt
                );

        return userExerciseAttemptMapper.toResponse(
                savedUserExerciseAttempt
        );
    }

    @Override
    public UserExerciseAttempt save(
            UserExerciseAttempt userExerciseAttempt) {

        return userExerciseAttemptRepository.save(
                userExerciseAttempt
        );
    }

    @Override
    public UserExerciseAttempt update(
            UserExerciseAttempt userExerciseAttempt) {

        if (userExerciseAttempt.getId() == null ||
                !userExerciseAttemptRepository.existsById(
                        userExerciseAttempt.getId())) {

            throw new ResourceNotFoundException(
                    "User exercise attempt does not exist"
            );
        }

        return userExerciseAttemptRepository.save(
                userExerciseAttempt
        );
    }

    @Override
    public Optional<UserExerciseAttempt> findById(Long id) {

        UserExerciseAttempt userExerciseAttempt =
                userExerciseAttemptRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User exercise attempt does not exist"
                                )
                        );

        return Optional.of(userExerciseAttempt);
    }

    @Override
    public List<UserExerciseAttempt> findAll() {

        return userExerciseAttemptRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        UserExerciseAttempt userExerciseAttempt =
                userExerciseAttemptRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User exercise attempt does not exist"
                                )
                        );

        userExerciseAttemptRepository.delete(
                userExerciseAttempt
        );
    }
}