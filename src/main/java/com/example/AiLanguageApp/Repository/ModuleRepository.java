package com.example.AiLanguageApp.Repository;

import com.example.AiLanguageApp.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Long> {
}