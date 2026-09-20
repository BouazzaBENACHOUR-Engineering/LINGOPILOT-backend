package com.example.AiLanguageApp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AiLanguageApp.DTO.Request.LevelRequest;
import com.example.AiLanguageApp.DTO.Response.LevelResponse;
import com.example.AiLanguageApp.Service.Interface.LevelService;
import com.example.AiLanguageApp.model.Level;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/levels")
public class LevelController {

    private final LevelService levelService;

    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @PostMapping
    public ResponseEntity<LevelResponse> create(
            @Valid @RequestBody LevelRequest request) {
        return ResponseEntity.ok(levelService.create(request));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(levelService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return levelService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Level> update(
            @PathVariable Long id,
            @RequestBody Level level) {

        level.setId(id);
        return ResponseEntity.ok(levelService.update(level));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        levelService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}