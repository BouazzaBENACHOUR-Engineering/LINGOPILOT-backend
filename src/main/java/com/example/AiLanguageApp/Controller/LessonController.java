package com.example.AiLanguageApp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AiLanguageApp.DTO.Request.LessonRequest;
import com.example.AiLanguageApp.DTO.Response.LessonResponse;
import com.example.AiLanguageApp.Service.Interface.LessonService;
import com.example.AiLanguageApp.model.Lesson;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    public ResponseEntity<LessonResponse> create(
            @Valid @RequestBody LessonRequest request) {
        return ResponseEntity.ok(lessonService.create(request));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(lessonService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return lessonService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lesson> update(
            @PathVariable Long id,
            @RequestBody Lesson lesson) {

        lesson.setId(id);
        return ResponseEntity.ok(lessonService.update(lesson));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        lessonService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}