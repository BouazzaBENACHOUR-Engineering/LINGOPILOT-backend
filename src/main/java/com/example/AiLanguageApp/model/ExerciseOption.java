package com.example.AiLanguageApp.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "exercise_options",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "UQ_exercise_options_sequence",
            columnNames = {"exercise_id", "sequence"}
        )
    }
)
public class ExerciseOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise_id;

    @Column(name = "option_text", nullable = false, length = 1000)
    private String option_text;

    @Column(name = "is_correct", nullable = false)
    private Boolean is_correct;

    @Column(name = "sequence", nullable = false)
    private Integer sequence;


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Exercise getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(Exercise exercise_id) {
        this.exercise_id = exercise_id;
    }

    public String getOption_text() {
        return option_text;
    }

    public void setOption_text(String option_text) {
        this.option_text = option_text;
    }

    public Boolean getIs_correct() {
        return is_correct;
    }

    public void setIs_correct(Boolean is_correct) {
        this.is_correct = is_correct;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}