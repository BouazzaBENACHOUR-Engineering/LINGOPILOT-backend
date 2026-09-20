package com.example.AiLanguageApp.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "lessons",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "UQ_lessons_sequence",
            columnNames = {"module_id", "sequence"}
        )
    }
)
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "module_id", nullable = false)
    private Module module_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill_id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description", columnDefinition = "nvarchar(max)")
    private String description;

    @Column(name = "sequence", nullable = false)
    private Integer sequence;

    @Column(name = "estimated_minutes")
    private Integer estimated_minutes;


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Module getModule_id() {
        return module_id;
    }

    public void setModule_id(Module module_id) {
        this.module_id = module_id;
    }

    public Skill getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(Skill skill_id) {
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