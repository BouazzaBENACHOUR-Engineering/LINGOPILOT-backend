package com.example.AiLanguageApp.Learning.DTO.Response;

public class LessonSectionResponse {
    private Long id;
    private String section_type;
    private String title;
    private String content;
    private Integer sequence;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSection_type() { return section_type; }
    public void setSection_type(String section_type) { this.section_type = section_type; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Integer getSequence() { return sequence; }
    public void setSequence(Integer sequence) { this.sequence = sequence; }
}
