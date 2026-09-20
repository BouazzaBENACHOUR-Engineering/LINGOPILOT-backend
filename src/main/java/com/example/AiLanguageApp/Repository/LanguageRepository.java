package com.example.AiLanguageApp.Repository;

import com.example.AiLanguageApp.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
}