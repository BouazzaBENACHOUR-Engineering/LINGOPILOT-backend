package com.example.AiLanguageApp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AiLanguageApp.DTO.Request.ModuleRequest;
import com.example.AiLanguageApp.DTO.Response.ModuleResponse;
import com.example.AiLanguageApp.Service.Interface.ModuleService;
import com.example.AiLanguageApp.model.Module;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/modules")
public class ModuleController {

    private final ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @PostMapping
    public ResponseEntity<ModuleResponse> create(
            @Valid @RequestBody ModuleRequest request) {
        return ResponseEntity.ok(moduleService.create(request));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(moduleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return moduleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Module> update(
            @PathVariable Long id,
            @RequestBody Module module) {

        module.setId(id);
        return ResponseEntity.ok(moduleService.update(module));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        moduleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}