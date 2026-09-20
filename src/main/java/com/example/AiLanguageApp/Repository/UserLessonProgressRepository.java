package com.example.AiLanguageApp.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.AiLanguageApp.model.UserLessonProgress;

public interface UserLessonProgressRepository
        extends JpaRepository<UserLessonProgress, Long> {

    @Query("""
            SELECT ulp
            FROM UserLessonProgress ulp
            WHERE ulp.user_id.id = :userId
              AND ulp.lesson_id.id = :lessonId
            """)
    Optional<UserLessonProgress> findForUserAndLesson(
            @Param("userId") Long userId,
            @Param("lessonId") Long lessonId
    );

    @Query("""
            SELECT ulp
            FROM UserLessonProgress ulp
            WHERE ulp.user_id.id = :userId
              AND ulp.lesson_id.module_id.course_id.language_id.id = :languageId
              AND ulp.lesson_id.module_id.course_id.level_id.id = :levelId
              AND UPPER(ulp.status) = 'COMPLETED'
            ORDER BY ulp.completed_at DESC
            """)
    List<UserLessonProgress> findCompletedByUserLanguageAndLevel(
            @Param("userId") Long userId,
            @Param("languageId") Long languageId,
            @Param("levelId") Long levelId
    );
}