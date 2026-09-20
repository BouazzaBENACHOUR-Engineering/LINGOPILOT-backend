USE AiLanguageApp;
GO

IF OBJECT_ID('dbo.lesson_sections', 'U') IS NULL
BEGIN
    CREATE TABLE dbo.lesson_sections (
        id BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
        lesson_id BIGINT NOT NULL,
        section_type VARCHAR(30) NOT NULL,
        title NVARCHAR(255) NULL,
        content NVARCHAR(MAX) NOT NULL,
        sequence INT NOT NULL,
        CONSTRAINT FK_lesson_sections_lesson FOREIGN KEY (lesson_id) REFERENCES dbo.lessons(id),
        CONSTRAINT UQ_lesson_sections_sequence UNIQUE (lesson_id, sequence)
    );
END;
GO
