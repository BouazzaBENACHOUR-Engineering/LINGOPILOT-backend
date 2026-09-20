package com.example.AiLanguageApp.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "modules",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "UQ_modules_sequence",
            columnNames = {"course_id", "sequence"}
        )
    }
)
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course_id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description", columnDefinition = "nvarchar(max)")
    private String description;

    @Column(name = "sequence", nullable = false)
    private Integer sequence;


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Course course_id) {
        this.course_id = course_id;
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
}