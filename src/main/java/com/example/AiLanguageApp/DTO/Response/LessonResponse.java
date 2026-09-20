package com.example.AiLanguageApp.DTO.Response;

public class LessonResponse {

    private Long id;
    private Long module_id;
    private Long skill_id;
    private String title;
    private String description;
    private Integer sequence;
    private Integer estimated_minutes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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