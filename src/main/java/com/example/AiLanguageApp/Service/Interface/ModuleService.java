package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.ModuleRequest;
import com.example.AiLanguageApp.DTO.Response.ModuleResponse;
import com.example.AiLanguageApp.model.Module;

import java.util.List;
import java.util.Optional;

public interface ModuleService {

    Module save(Module module);

    Module update(Module module);

    Optional<Module> findById(Long id);

    List<Module> findAll();
    
    ModuleResponse create(ModuleRequest request);

    void deleteById(Long id);
}