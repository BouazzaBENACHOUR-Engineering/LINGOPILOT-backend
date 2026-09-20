package com.example.AiLanguageApp.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.AiLanguageApp.model.UserSkillProgress;

public interface UserSkillProgressRepository
        extends JpaRepository<UserSkillProgress, Long> {

    @Query("""
            SELECT usp
            FROM UserSkillProgress usp
            WHERE usp.user_id.id = :userId
            AND usp.language_id.id = :languageId
            AND usp.skill_id.id = :skillId
            """)
    Optional<UserSkillProgress> findByUserLanguageAndSkill(
            @Param("userId") Long userId,
            @Param("languageId") Long languageId,
            @Param("skillId") Long skillId
    );

    @Query("""
            SELECT usp
            FROM UserSkillProgress usp
            WHERE usp.user_id.id = :userId
            AND usp.language_id.id = :languageId
            """)
    List<UserSkillProgress> findByUserAndLanguage(
            @Param("userId") Long userId,
            @Param("languageId") Long languageId
    );
}