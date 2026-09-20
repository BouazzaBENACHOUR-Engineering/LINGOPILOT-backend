package com.example.AiLanguageApp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AiLanguageApp.DTO.Request.ExerciseRequest;
import com.example.AiLanguageApp.DTO.Response.ExerciseResponse;
import com.example.AiLanguageApp.Service.Interface.ExerciseService;
import com.example.AiLanguageApp.model.Exercise;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping
    public ResponseEntity<ExerciseResponse> create(
            @Valid @RequestBody ExerciseRequest request) {
        return ResponseEntity.ok(exerciseService.create(request));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(exerciseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return exerciseService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercise> update(
            @PathVariable Long id,
            @RequestBody Exercise exercise) {

        exercise.setId(id);
        return ResponseEntity.ok(exerciseService.update(exercise));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        exerciseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}