package com.example.AiLanguageApp.AI.DTO;

public class LessonExerciseItem {

    private Long exercise_id;
    private String type;
    private String question;
    private Integer difficulty;
    private Integer points;
    private Integer sequence;

    public Long getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(Long exercise_id) {
        this.exercise_id = exercise_id;
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
}