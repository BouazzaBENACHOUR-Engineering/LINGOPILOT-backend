package com.example.AiLanguageApp.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_exercise_attempts")
public class UserExerciseAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise_id;

    @Column(name = "answer", columnDefinition = "nvarchar(max)")
    private String answer;

    @Column(name = "is_correct")
    private Boolean is_correct;

    @Column(name = "score", precision = 5, scale = 2)
    private BigDecimal score;

    @Column(name = "attempted_at", nullable = false)
    private LocalDateTime attempted_at;


    // Getters and Setters

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

    public Exercise getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(Exercise exercise_id) {
        this.exercise_id = exercise_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getIs_correct() {
        return is_correct;
    }

    public void setIs_correct(Boolean is_correct) {
        this.is_correct = is_correct;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public LocalDateTime getAttempted_at() {
        return attempted_at;
    }

    public void setAttempted_at(LocalDateTime attempted_at) {
        this.attempted_at = attempted_at;
    }
}