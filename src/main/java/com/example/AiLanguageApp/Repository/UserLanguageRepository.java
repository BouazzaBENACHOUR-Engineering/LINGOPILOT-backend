package com.example.AiLanguageApp.Repository;

import com.example.AiLanguageApp.model.UserLanguage;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserLanguageRepository extends JpaRepository<UserLanguage, Long> {
	
	@Query("""
	        SELECT ul
	        FROM UserLanguage ul
	        WHERE ul.user_id.id = :userId
	        AND ul.language_id.id = :languageId
	        """)
	Optional<UserLanguage> findByUserAndLanguage(
	        @Param("userId") Long userId,
	        @Param("languageId") Long languageId
	);
}