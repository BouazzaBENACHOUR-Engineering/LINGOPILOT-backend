package com.example.AiLanguageApp.Mapper;

import org.springframework.stereotype.Component;

import com.example.AiLanguageApp.DTO.Response.CourseResponse;
import com.example.AiLanguageApp.model.Course;

@Component
public class CourseMapper {

    public CourseResponse toResponse(Course course) {

        if (course == null) {
            return null;
        }

        CourseResponse response = new CourseResponse();

        response.setId(course.getId());

        if (course.getLanguage_id() != null) {
            response.setLanguage_id(
                    course.getLanguage_id().getId()
            );
        }

        if (course.getLevel_id() != null) {
            response.setLevel_id(
                    course.getLevel_id().getId()
            );
        }

        response.setTitle(course.getTitle());
        response.setDescription(course.getDescription());
        response.setIs_active(course.getIs_active());
        response.setCreated_at(course.getCreated_at());

        return response;
    }
}