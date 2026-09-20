package com.example.AiLanguageApp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AiLanguageApp.DTO.Request.UserSkillProgressRequest;
import com.example.AiLanguageApp.DTO.Response.UserSkillProgressResponse;
import com.example.AiLanguageApp.Service.Interface.UserSkillProgressService;
import com.example.AiLanguageApp.model.UserSkillProgress;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user-skill-progress")
public class UserSkillProgressController {

    private final UserSkillProgressService userSkillProgressService;

    public UserSkillProgressController(
            UserSkillProgressService userSkillProgressService) {
        this.userSkillProgressService = userSkillProgressService;
    }

    @PostMapping
    public ResponseEntity<UserSkillProgressResponse> create(
            @Valid @RequestBody UserSkillProgressRequest request) {
        return ResponseEntity.ok(
                userSkillProgressService.create(request)
        );
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                userSkillProgressService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return userSkillProgressService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserSkillProgress> update(
            @PathVariable Long id,
            @RequestBody UserSkillProgress userSkillProgress) {

        userSkillProgress.setId(id);

        return ResponseEntity.ok(
                userSkillProgressService.update(userSkillProgress)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userSkillProgressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}