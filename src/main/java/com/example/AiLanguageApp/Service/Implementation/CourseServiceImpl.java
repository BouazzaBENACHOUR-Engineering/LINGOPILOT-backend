package com.example.AiLanguageApp.Service.Implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.CourseRequest;
import com.example.AiLanguageApp.DTO.Response.CourseResponse;
import com.example.AiLanguageApp.Mapper.CourseMapper;
import com.example.AiLanguageApp.Repository.CourseRepository;
import com.example.AiLanguageApp.Repository.LanguageRepository;
import com.example.AiLanguageApp.Repository.LevelRepository;
import com.example.AiLanguageApp.Service.Interface.CourseService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.Course;
import com.example.AiLanguageApp.model.Language;
import com.example.AiLanguageApp.model.Level;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final LanguageRepository languageRepository;
    private final LevelRepository levelRepository;
    private final CourseMapper courseMapper;

    public CourseServiceImpl(
            CourseRepository courseRepository,
            LanguageRepository languageRepository,
            LevelRepository levelRepository,
            CourseMapper courseMapper) {

        this.courseRepository = courseRepository;
        this.languageRepository = languageRepository;
        this.levelRepository = levelRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public CourseResponse create(
            CourseRequest request) {

        Language language =
                languageRepository
                        .findById(request.getLanguage_id())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Language does not exist"
                                )
                        );

        Level level =
                levelRepository
                        .findById(request.getLevel_id())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Level does not exist"
                                )
                        );

        Course course = new Course();

        course.setLanguage_id(language);
        course.setLevel_id(level);
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setIs_active(request.getIs_active());
        course.setCreated_at(LocalDateTime.now());

        Course savedCourse =
                courseRepository.save(course);

        return courseMapper.toResponse(savedCourse);
    }

    @Override
    public Course save(
            Course course) {

        return courseRepository.save(course);
    }

    @Override
    public Course update(
            Course course) {

        if (course.getId() == null ||
                !courseRepository.existsById(
                        course.getId())) {

            throw new ResourceNotFoundException(
                    "Course does not exist"
            );
        }

        return courseRepository.save(course);
    }

    @Override
    public Optional<Course> findById(Long id) {

        Course course =
                courseRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Course does not exist"
                                )
                        );

        return Optional.of(course);
    }

    @Override
    public List<Course> findAll() {

        return courseRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        Course course =
                courseRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Course does not exist"
                                )
                        );

        courseRepository.delete(course);
    }
}