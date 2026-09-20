package com.example.AiLanguageApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.AiLanguageApp.model.ExerciseOption;

public interface ExerciseOptionRepository
        extends JpaRepository<ExerciseOption, Long> {

    @Query("""
            SELECT eo
            FROM ExerciseOption eo
            WHERE eo.exercise_id.id = :exerciseId
            ORDER BY eo.sequence ASC
            """)
    List<ExerciseOption> findByExercise(
            @Param("exerciseId") Long exerciseId
    );
}