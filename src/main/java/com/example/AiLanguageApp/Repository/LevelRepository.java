package com.example.AiLanguageApp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.AiLanguageApp.model.Level;

public interface LevelRepository
        extends JpaRepository<Level, Long> {

    Optional<Level> findByCode(
            String code
    );

    @Query("""
            SELECT l
            FROM Level l
            WHERE l.id = :levelId
            """)
    Optional<Level> findLevel(
            @Param("levelId") Long levelId
    );
}