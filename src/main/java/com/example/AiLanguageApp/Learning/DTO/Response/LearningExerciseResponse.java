package com.example.AiLanguageApp.Learning.DTO.Response;

public class LearningExerciseResponse {

    private Long id;

    private String type;

    private String question;

    private Integer difficulty;

    private Integer points;

    private Integer sequence;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getQuestion() { return question; }

    public void setQuestion(String question) { this.question = question; }

    public Integer getDifficulty() { return difficulty; }

    public void setDifficulty(Integer difficulty) { this.difficulty = difficulty; }

    public Integer getPoints() { return points; }

    public void setPoints(Integer points) { this.points = points; }

    public Integer getSequence() { return sequence; }

    public void setSequence(Integer sequence) { this.sequence = sequence; }

}