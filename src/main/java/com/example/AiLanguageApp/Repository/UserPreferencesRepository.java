package com.example.AiLanguageApp.Repository;

import com.example.AiLanguageApp.model.UserPreferences;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserPreferencesRepository extends JpaRepository<UserPreferences, Long> {
	
	@Query("""
	        SELECT p
	        FROM UserPreferences p
	        WHERE p.user.id = :userId
	        """)
	Optional<UserPreferences> findByUserId(
	        @Param("userId") Long userId
	);
}