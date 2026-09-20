package com.example.AiLanguageApp.Repository;

import com.example.AiLanguageApp.model.UserVocabulary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVocabularyRepository
        extends JpaRepository<UserVocabulary, Long> {
}