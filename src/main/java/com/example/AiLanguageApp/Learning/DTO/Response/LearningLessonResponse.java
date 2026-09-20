package com.example.AiLanguageApp.Learning.DTO.Response;

import java.util.ArrayList;
import java.util.List;

public class LearningLessonResponse {
    private Object lesson;
    private List<LessonSectionResponse> sections = new ArrayList<>();
    private List<LearningExerciseResponse> exercises = new ArrayList<>();

    public Object getLesson() { return lesson; }
    public void setLesson(Object lesson) { this.lesson = lesson; }
    public List<LessonSectionResponse> getSections() { return sections; }
    public void setSections(List<LessonSectionResponse> sections) { this.sections = sections; }
    public List<LearningExerciseResponse> getExercises() { return exercises; }
    public void setExercises(List<LearningExerciseResponse> exercises) { this.exercises = exercises; }
}
