package com.example.AiLanguageApp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "exercises",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "UQ_exercises_lesson_sequence",
            columnNames = {"lesson_id", "sequence"}
        )
    }
)
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson_id;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "question", nullable = false, columnDefinition = "nvarchar(max)")
    private String question;

    @Column(name = "correct_answer", length = 500)
    private String correct_answer;

    @Column(name = "difficulty")
    private Integer difficulty;

    @Column(name = "points", nullable = false)
    private Integer points;

    @Column(name = "sequence", nullable = false)
    private Integer sequence;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lesson getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(Lesson lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}