package com.example.AiLanguageApp.AI.DTO;

import java.math.BigDecimal;

public class LessonRecommendation {

    private Long lesson_id;
    private String lesson_title;
    private String lesson_description;
    private Integer estimated_minutes;

    private Long skill_id;
    private String skill_name;
    private BigDecimal skill_score;

    private Long module_id;
    private String module_title;

    private Long course_id;
    private String course_title;

    private String progress_status;
    private BigDecimal completion_percentage;

    private String reason;

    public Long getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(Long lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getLesson_title() {
        return lesson_title;
    }

    public void setLesson_title(String lesson_title) {
        this.lesson_title = lesson_title;
    }

    public String getLesson_description() {
        return lesson_description;
    }

    public void setLesson_description(String lesson_description) {
        this.lesson_description = lesson_description;
    }

    public Integer getEstimated_minutes() {
        return estimated_minutes;
    }

    public void setEstimated_minutes(Integer estimated_minutes) {
        this.estimated_minutes = estimated_minutes;
    }

    public Long getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(Long skill_id) {
        this.skill_id = skill_id;
    }

    public String getSkill_name() {
        return skill_name;
    }

    public void setSkill_name(String skill_name) {
        this.skill_name = skill_name;
    }

    public BigDecimal getSkill_score() {
        return skill_score;
    }

    public void setSkill_score(BigDecimal skill_score) {
        this.skill_score = skill_score;
    }

    public Long getModule_id() {
        return module_id;
    }

    public void setModule_id(Long module_id) {
        this.module_id = module_id;
    }

    public String getModule_title() {
        return module_title;
    }

    public void setModule_title(String module_title) {
        this.module_title = module_title;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public String getProgress_status() {
        return progress_status;
    }

    public void setProgress_status(String progress_status) {
        this.progress_status = progress_status;
    }

    public BigDecimal getCompletion_percentage() {
        return completion_percentage;
    }

    public void setCompletion_percentage(BigDecimal completion_percentage) {
        this.completion_percentage = completion_percentage;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}