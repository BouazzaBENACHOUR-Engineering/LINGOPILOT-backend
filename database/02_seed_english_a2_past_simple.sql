USE AiLanguageApp;
GO

DECLARE @language_id BIGINT = (SELECT TOP 1 id FROM languages WHERE UPPER(code) IN ('EN', 'ENG'));
DECLARE @level_id BIGINT = (SELECT TOP 1 id FROM levels WHERE UPPER(code) = 'A2');
DECLARE @skill_id BIGINT = (SELECT TOP 1 id FROM skills WHERE UPPER(name) LIKE '%GRAMMAR%');

IF @language_id IS NULL OR @level_id IS NULL OR @skill_id IS NULL
    THROW 50001, 'English language, A2 level and Grammar skill must exist before running this seed.', 1;

DECLARE @course_id BIGINT = (
    SELECT TOP 1 id FROM courses
    WHERE language_id = @language_id AND level_id = @level_id AND title = 'English A2 Grammar'
);

IF @course_id IS NULL
BEGIN
    INSERT INTO courses(language_id, level_id, title, description, is_active, created_at)
    VALUES(@language_id, @level_id, 'English A2 Grammar', 'Grammar lessons adapted to English A2 learners.', 1, SYSDATETIME());
    SET @course_id = SCOPE_IDENTITY();
END;

DECLARE @module_id BIGINT = (
    SELECT TOP 1 id FROM modules WHERE course_id = @course_id AND title = 'Talking about the past'
);

IF @module_id IS NULL
BEGIN
    INSERT INTO modules(course_id, title, description, sequence)
    VALUES(@course_id, 'Talking about the past', 'Learn to describe completed events in the past.', 1);
    SET @module_id = SCOPE_IDENTITY();
END;

DECLARE @lesson_id BIGINT = (
    SELECT TOP 1 id FROM lessons WHERE module_id = @module_id AND title = 'Past Simple'
);

IF @lesson_id IS NULL
BEGIN
    INSERT INTO lessons(module_id, skill_id, title, description, sequence, estimated_minutes)
    VALUES(
        @module_id,
        @skill_id,
        'Past Simple',
        'Learn how to talk about completed actions and events in the past.',
        1,
        15
    );
    SET @lesson_id = SCOPE_IDENTITY();
END;

IF NOT EXISTS (SELECT 1 FROM lesson_sections WHERE lesson_id = @lesson_id)
BEGIN
    INSERT INTO lesson_sections(lesson_id, section_type, title, content, sequence) VALUES
    (@lesson_id, 'THEORY', 'When do we use the Past Simple?', 'Use the Past Simple for actions and events that started and finished in the past. Common time expressions include yesterday, last week, last year and two days ago.', 1),
    (@lesson_id, 'RULE', 'Regular verbs', 'For most regular verbs, add -ed: work → worked, visit → visited, play → played. Example: I visited my friend yesterday.', 2),
    (@lesson_id, 'RULE', 'Irregular verbs', 'Some verbs change form and must be learned: go → went, see → saw, have → had, take → took, come → came.', 3),
    (@lesson_id, 'EXAMPLE', 'Affirmative sentences', 'I worked yesterday.\nShe went to school last Monday.\nWe saw a great movie last night.', 4),
    (@lesson_id, 'EXAMPLE', 'Negative and questions', 'Negative: I did not go to work. / I didn''t go to work.\nQuestion: Did you watch the match?\nAfter did or didn''t, use the base form of the verb.', 5),
    (@lesson_id, 'TIP', 'Remember this', 'Do not say “Did you went?”. Say “Did you go?”. DID already carries the past tense.', 6),
    (@lesson_id, 'SUMMARY', 'Lesson summary', 'Use Past Simple for completed past actions. Regular verbs usually end in -ed. Learn common irregular forms. Use did/didn''t + base verb for questions and negatives.', 7);
END;

DECLARE @ex1 BIGINT = (SELECT TOP 1 id FROM exercises WHERE lesson_id = @lesson_id AND sequence = 1);
IF @ex1 IS NULL
BEGIN
    INSERT INTO exercises(lesson_id, type, question, correct_answer, difficulty, points, sequence, created_at)
    VALUES(@lesson_id, 'FILL_BLANK', 'Yesterday, I ___ to the cinema with my friends.', 'went', 2, 10, 1, SYSDATETIME());
END;

DECLARE @ex2 BIGINT = (SELECT TOP 1 id FROM exercises WHERE lesson_id = @lesson_id AND sequence = 2);
IF @ex2 IS NULL
BEGIN
    INSERT INTO exercises(lesson_id, type, question, correct_answer, difficulty, points, sequence, created_at)
    VALUES(@lesson_id, 'MULTIPLE_CHOICE', 'Choose the correct sentence.', 'She visited her grandmother yesterday.', 2, 10, 2, SYSDATETIME());
    SET @ex2 = SCOPE_IDENTITY();

    INSERT INTO exercise_options(exercise_id, option_text, is_correct, sequence) VALUES
    (@ex2, 'She visit her grandmother yesterday.', 0, 1),
    (@ex2, 'She visited her grandmother yesterday.', 1, 2),
    (@ex2, 'She visits her grandmother yesterday.', 0, 3);
END;

DECLARE @ex3 BIGINT = (SELECT TOP 1 id FROM exercises WHERE lesson_id = @lesson_id AND sequence = 3);
IF @ex3 IS NULL
BEGIN
    INSERT INTO exercises(lesson_id, type, question, correct_answer, difficulty, points, sequence, created_at)
    VALUES(@lesson_id, 'FREE_TEXT', 'Write one sentence about something you did yesterday. Use a Past Simple verb.', 'I went to work yesterday.|I worked yesterday.|I studied yesterday.', 2, 10, 3, SYSDATETIME());
END;
GO
