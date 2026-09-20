package com.example.AiLanguageApp.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.LessonRequest;
import com.example.AiLanguageApp.DTO.Response.LessonResponse;
import com.example.AiLanguageApp.Repository.LessonRepository;
import com.example.AiLanguageApp.Service.Interface.LessonService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.Lesson;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    public LessonServiceImpl(
            LessonRepository lessonRepository) {

        this.lessonRepository = lessonRepository;
    }

    @Override
    public LessonResponse create(
            LessonRequest request) {

        Lesson lesson = new Lesson();

        Lesson savedLesson =
                lessonRepository.save(lesson);

        LessonResponse response =
                new LessonResponse();

        response.setId(savedLesson.getId());

        return response;
    }

    @Override
    public Lesson save(
            Lesson lesson) {

        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson update(
            Lesson lesson) {

        if (lesson.getId() == null ||
                !lessonRepository.existsById(
                        lesson.getId())) {

            throw new ResourceNotFoundException(
                    "Lesson does not exist"
            );
        }

        return lessonRepository.save(lesson);
    }

    @Override
    public Optional<Lesson> findById(Long id) {

        Lesson lesson =
                lessonRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Lesson does not exist"
                                )
                        );

        return Optional.of(lesson);
    }

    @Override
    public List<Lesson> findAll() {

        return lessonRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        Lesson lesson =
                lessonRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Lesson does not exist"
                                )
                        );

        lessonRepository.delete(lesson);
    }
}