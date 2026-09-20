package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.LevelRequest;
import com.example.AiLanguageApp.DTO.Response.LevelResponse;
import com.example.AiLanguageApp.model.Level;

import java.util.List;
import java.util.Optional;

public interface LevelService {

    Level save(Level level);

    Level update(Level level);

    Optional<Level> findById(Long id);

    List<Level> findAll();
    
    LevelResponse create(LevelRequest request);

    void deleteById(Long id);
}