package com.example.AiLanguageApp.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "lesson_sections",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "UQ_lesson_sections_sequence",
            columnNames = {"lesson_id", "sequence"}
        )
    }
)
public class LessonSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson_id;

    @Column(name = "section_type", nullable = false, length = 30)
    private String section_type;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "nvarchar(max)")
    private String content;

    @Column(name = "sequence", nullable = false)
    private Integer sequence;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Lesson getLesson_id() { return lesson_id; }
    public void setLesson_id(Lesson lesson_id) { this.lesson_id = lesson_id; }
    public String getSection_type() { return section_type; }
    public void setSection_type(String section_type) { this.section_type = section_type; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Integer getSequence() { return sequence; }
    public void setSequence(Integer sequence) { this.sequence = sequence; }
}
