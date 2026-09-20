package com.example.AiLanguageApp.DTOs.ResponseDTO;

public class ExerciseResponseDTO {
	
	private Long id;
	private Long lesson_id;
	private String type;
	private String question;
	private String correct_answer;
	private Integer points;
	
	public ExerciseResponseDTO() {
	}
	
	public Long getId() {
	    return id;
	}
	
	public void setId(Long id) {
	    this.id = id;
	}
	
	public Long getLesson_id() {
	    return lesson_id;
	}
	
	public void setLesson_id(Long lesson_id) {
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
	
	public Integer getPoints() {
	    return points;
	}
	
	public void setPoints(Integer points) {
	    this.points = points;
	}

}
