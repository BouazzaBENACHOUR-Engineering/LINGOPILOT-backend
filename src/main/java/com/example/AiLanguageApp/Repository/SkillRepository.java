package com.example.AiLanguageApp.Repository;

import com.example.AiLanguageApp.model.Skill;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
	
	Optional<Skill> findByNameIgnoreCase(String name);
}