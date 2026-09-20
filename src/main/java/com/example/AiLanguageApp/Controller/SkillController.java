package com.example.AiLanguageApp.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AiLanguageApp.DTO.Request.SkillRequest;
import com.example.AiLanguageApp.DTO.Response.SkillResponse;
import com.example.AiLanguageApp.Service.Interface.SkillService;
import com.example.AiLanguageApp.model.Skill;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public ResponseEntity<SkillResponse> create(
            @Valid @RequestBody SkillRequest request) {
        return ResponseEntity.ok(skillService.create(request));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(skillService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return skillService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Skill> update(
            @PathVariable Long id,
            @RequestBody Skill skill) {

        skill.setId(id);
        return ResponseEntity.ok(skillService.update(skill));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        skillService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}