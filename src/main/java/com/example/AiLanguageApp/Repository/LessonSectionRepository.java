package com.example.AiLanguageApp.Repository;

import com.example.AiLanguageApp.model.LessonSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonSectionRepository extends JpaRepository<LessonSection, Long> {

    @Query("select s from LessonSection s where s.lesson_id.id = :lessonId order by s.sequence")
    List<LessonSection> findByLessonOrdered(@Param("lessonId") Long lessonId);
}
