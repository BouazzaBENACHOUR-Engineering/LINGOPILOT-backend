package com.example.AiLanguageApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vocabulary")
public class Vocabulary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @Column(name = "word", nullable = false, length = 255)
    private String word;

    @Column(name = "translation", length = 255)
    private String translation;

    @Column(name = "definition", columnDefinition = "nvarchar(max)")
    private String definition;

    @Column(name = "example_sentence", columnDefinition = "nvarchar(max)")
    private String example_sentence;

    @Column(name = "audio_url", length = 1000)
    private String audio_url;

    @Column(name = "difficulty", nullable = false)
    private Integer difficulty;

    // Constructors

    public Vocabulary() {
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getExample_sentence() {
        return example_sentence;
    }

    public void setExample_sentence(String example_sentence) {
        this.example_sentence = example_sentence;
    }

    public String getAudio_url() {
        return audio_url;
    }

    public void setAudio_url(String audio_url) {
        this.audio_url = audio_url;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }
}