package com.example.AiLanguageApp.Repository;

import com.example.AiLanguageApp.model.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VocabularyRepository extends JpaRepository<Vocabulary, Long> {
}