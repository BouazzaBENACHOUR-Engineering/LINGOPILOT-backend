package com.example.AiLanguageApp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AiLanguageApp.DTO.Request.UserExerciseAttemptRequest;
import com.example.AiLanguageApp.DTO.Response.UserExerciseAttemptResponse;
import com.example.AiLanguageApp.Service.Interface.UserExerciseAttemptService;
import com.example.AiLanguageApp.model.UserExerciseAttempt;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user-exercise-attempts")
public class UserExerciseAttemptController {

    private final UserExerciseAttemptService userExerciseAttemptService;

    public UserExerciseAttemptController(
            UserExerciseAttemptService userExerciseAttemptService) {
        this.userExerciseAttemptService = userExerciseAttemptService;
    }

    @PostMapping
    public ResponseEntity<UserExerciseAttemptResponse> create(
            @Valid @RequestBody UserExerciseAttemptRequest request) {
        return ResponseEntity.ok(
                userExerciseAttemptService.create(request)
        );
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                userExerciseAttemptService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return userExerciseAttemptService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserExerciseAttempt> update(
            @PathVariable Long id,
            @RequestBody UserExerciseAttempt userExerciseAttempt) {

        userExerciseAttempt.setId(id);

        return ResponseEntity.ok(
                userExerciseAttemptService.update(userExerciseAttempt)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userExerciseAttemptService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}