package com.example.AiLanguageApp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AiLanguageApp.DTO.Request.ExerciseOptionRequest;
import com.example.AiLanguageApp.DTO.Response.ExerciseOptionResponse;
import com.example.AiLanguageApp.Service.Interface.ExerciseOptionService;
import com.example.AiLanguageApp.model.ExerciseOption;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/exercise-options")
public class ExerciseOptionController {

    private final ExerciseOptionService exerciseOptionService;

    public ExerciseOptionController(ExerciseOptionService exerciseOptionService) {
        this.exerciseOptionService = exerciseOptionService;
    }

    @PostMapping
    public ResponseEntity<ExerciseOptionResponse> create(
            @Valid @RequestBody ExerciseOptionRequest request) {
        return ResponseEntity.ok(exerciseOptionService.create(request));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(exerciseOptionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return exerciseOptionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExerciseOption> update(
            @PathVariable Long id,
            @RequestBody ExerciseOption exerciseOption) {

        exerciseOption.setId(id);
        return ResponseEntity.ok(exerciseOptionService.update(exerciseOption));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        exerciseOptionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}