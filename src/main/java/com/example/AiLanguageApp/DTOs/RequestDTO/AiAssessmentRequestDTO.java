package com.example.AiLanguageApp.DTOs.RequestDTO;

import jakarta.validation.constraints.NotNull;

public class AiAssessmentRequestDTO {

    @NotNull(message = "Conversation ID is required")
    private Long conversation_id;

    @NotNull(message = "Skill ID is required")
    private Long skill_id;

    @NotNull(message = "Level ID is required")
    private Long level_id;

    public Long getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(Long conversation_id) {
        this.conversation_id = conversation_id;
    }

    public Long getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(Long skill_id) {
        this.skill_id = skill_id;
    }

    public Long getLevel_id() {
        return level_id;
    }

    public void setLevel_id(Long level_id) {
        this.level_id = level_id;
    }
}