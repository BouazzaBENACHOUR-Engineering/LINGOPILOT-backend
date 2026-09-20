package com.example.AiLanguageApp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AiLanguageApp.DTO.Request.UserLessonProgressRequest;
import com.example.AiLanguageApp.DTO.Response.UserLessonProgressResponse;
import com.example.AiLanguageApp.Service.Interface.UserLessonProgressService;
import com.example.AiLanguageApp.model.UserLessonProgress;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user-lesson-progress")
public class UserLessonProgressController {

    private final UserLessonProgressService userLessonProgressService;

    public UserLessonProgressController(
            UserLessonProgressService userLessonProgressService) {
        this.userLessonProgressService = userLessonProgressService;
    }

    @PostMapping
    public ResponseEntity<UserLessonProgressResponse> create(
            @Valid @RequestBody UserLessonProgressRequest request) {
        return ResponseEntity.ok(
                userLessonProgressService.create(request)
        );
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                userLessonProgressService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return userLessonProgressService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserLessonProgress> update(
            @PathVariable Long id,
            @RequestBody UserLessonProgress userLessonProgress) {

        userLessonProgress.setId(id);

        return ResponseEntity.ok(
                userLessonProgressService.update(userLessonProgress)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userLessonProgressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}