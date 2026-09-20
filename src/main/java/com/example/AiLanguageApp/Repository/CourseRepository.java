package com.example.AiLanguageApp.Repository;

import com.example.AiLanguageApp.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}