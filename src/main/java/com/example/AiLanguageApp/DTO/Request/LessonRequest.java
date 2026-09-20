package com.example.AiLanguageApp.DTO.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LessonRequest {

    @NotNull(message = "module_id is required")
    private Long module_id;

    @NotNull(message = "skill_id is required")
    private Long skill_id;

    @NotBlank(message = "title is required")
    @Size(max = 255, message = "title must not exceed 255 characters")
    private String title;

    private String description;

    @NotNull(message = "sequence is required")
    @Min(value = 1, message = "sequence must be at least 1")
    private Integer sequence;

    @Min(value = 0, message = "estimated_minutes must not be negative")
    private Integer estimated_minutes;

    public Long getModule_id() {
        return module_id;
    }

    public void setModule_id(Long module_id) {
        this.module_id = module_id;
    }

    public Long getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(Long skill_id) {
        this.skill_id = skill_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getEstimated_minutes() {
        return estimated_minutes;
    }

    public void setEstimated_minutes(Integer estimated_minutes) {
        this.estimated_minutes = estimated_minutes;
    }
}