package com.example.AiLanguageApp.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "user_lesson_progress",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "UQ_user_lesson_progress",
            columnNames = {"user_id", "lesson_id"}
        )
    }
)
public class UserLessonProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson_id;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "completion_percentage", nullable = false, precision = 5, scale = 2)
    private BigDecimal completion_percentage;

    @Column(name = "score", precision = 5, scale = 2)
    private BigDecimal score;

    @Column(name = "started_at")
    private LocalDateTime started_at;

    @Column(name = "completed_at")
    private LocalDateTime completed_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Lesson getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(Lesson lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getCompletion_percentage() {
        return completion_percentage;
    }

    public void setCompletion_percentage(BigDecimal completion_percentage) {
        this.completion_percentage = completion_percentage;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public LocalDateTime getStarted_at() {
        return started_at;
    }

    public void setStarted_at(LocalDateTime started_at) {
        this.started_at = started_at;
    }

    public LocalDateTime getCompleted_at() {
        return completed_at;
    }

    public void setCompleted_at(LocalDateTime completed_at) {
        this.completed_at = completed_at;
    }
}