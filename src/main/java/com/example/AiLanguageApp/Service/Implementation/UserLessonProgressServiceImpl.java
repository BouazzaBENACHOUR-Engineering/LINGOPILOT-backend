package com.example.AiLanguageApp.Service.Implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.UserLessonProgressRequest;
import com.example.AiLanguageApp.DTO.Response.UserLessonProgressResponse;
import com.example.AiLanguageApp.Mapper.UserLessonProgressMapper;
import com.example.AiLanguageApp.Repository.LessonRepository;
import com.example.AiLanguageApp.Repository.UserLessonProgressRepository;
import com.example.AiLanguageApp.Repository.UserRepository;
import com.example.AiLanguageApp.Service.Interface.UserLessonProgressService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.Lesson;
import com.example.AiLanguageApp.model.User;
import com.example.AiLanguageApp.model.UserLessonProgress;

@Service
public class UserLessonProgressServiceImpl
        implements UserLessonProgressService {

    private final UserLessonProgressRepository
            userLessonProgressRepository;

    private final UserRepository
            userRepository;

    private final LessonRepository
            lessonRepository;

    private final UserLessonProgressMapper
            userLessonProgressMapper;

    public UserLessonProgressServiceImpl(
            UserLessonProgressRepository userLessonProgressRepository,
            UserRepository userRepository,
            LessonRepository lessonRepository,
            UserLessonProgressMapper userLessonProgressMapper) {

        this.userLessonProgressRepository =
                userLessonProgressRepository;

        this.userRepository =
                userRepository;

        this.lessonRepository =
                lessonRepository;

        this.userLessonProgressMapper =
                userLessonProgressMapper;
    }

    @Override
    public UserLessonProgressResponse create(
            UserLessonProgressRequest request) {

        User user =
                userRepository
                        .findById(request.getUser_id())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User does not exist"
                                )
                        );

        Lesson lesson =
                lessonRepository
                        .findById(request.getLesson_id())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Lesson does not exist"
                                )
                        );

        UserLessonProgress userLessonProgress =
                new UserLessonProgress();

        userLessonProgress.setUser_id(user);
        userLessonProgress.setLesson_id(lesson);
        userLessonProgress.setStarted_at(
                LocalDateTime.now()
        );

        UserLessonProgress savedUserLessonProgress =
                userLessonProgressRepository.save(
                        userLessonProgress
                );

        return userLessonProgressMapper.toResponse(
                savedUserLessonProgress
        );
    }

    @Override
    public UserLessonProgress save(
            UserLessonProgress userLessonProgress) {

        return userLessonProgressRepository.save(
                userLessonProgress
        );
    }

    @Override
    public UserLessonProgress update(
            UserLessonProgress userLessonProgress) {

        if (userLessonProgress.getId() == null ||
                !userLessonProgressRepository.existsById(
                        userLessonProgress.getId())) {

            throw new ResourceNotFoundException(
                    "User lesson progress does not exist"
            );
        }

        return userLessonProgressRepository.save(
                userLessonProgress
        );
    }

    @Override
    public Optional<UserLessonProgress> findById(Long id) {

        UserLessonProgress userLessonProgress =
                userLessonProgressRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User lesson progress does not exist"
                                )
                        );

        return Optional.of(
                userLessonProgress
        );
    }

    @Override
    public List<UserLessonProgress> findAll() {

        return userLessonProgressRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        UserLessonProgress userLessonProgress =
                userLessonProgressRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User lesson progress does not exist"
                                )
                        );

        userLessonProgressRepository.delete(
                userLessonProgress
        );
    }
}