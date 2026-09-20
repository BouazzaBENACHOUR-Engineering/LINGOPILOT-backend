package com.example.AiLanguageApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.AiLanguageApp.model.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query("""
            SELECT l
            FROM Lesson l
            JOIN FETCH l.skill_id s
            JOIN FETCH l.module_id m
            JOIN FETCH m.course_id c
            JOIN FETCH c.language_id lang
            JOIN FETCH c.level_id lvl
            WHERE lang.id = :languageId
              AND lvl.id = :levelId
              AND s.id = :skillId
              AND c.is_active = true
            ORDER BY m.sequence ASC, l.sequence ASC
            """)
    List<Lesson> findRecommendationCandidates(
            @Param("languageId") Long languageId,
            @Param("levelId") Long levelId,
            @Param("skillId") Long skillId
    );
}