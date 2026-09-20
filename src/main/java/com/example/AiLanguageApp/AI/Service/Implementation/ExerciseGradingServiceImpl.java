package com.example.AiLanguageApp.AI.Service.Implementation;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.AI.Grading.ExerciseGradeResult;
import com.example.AiLanguageApp.AI.Service.ExerciseGradingService;
import com.example.AiLanguageApp.AI.Service.WritingExerciseGradingService;
import com.example.AiLanguageApp.Repository.ExerciseOptionRepository;
import com.example.AiLanguageApp.model.Exercise;
import com.example.AiLanguageApp.model.ExerciseOption;

@Service
public class ExerciseGradingServiceImpl
        implements ExerciseGradingService {

    private final ExerciseOptionRepository exerciseOptionRepository;

    private final WritingExerciseGradingService writingExerciseGradingService;

    public ExerciseGradingServiceImpl(
            ExerciseOptionRepository exerciseOptionRepository,
            WritingExerciseGradingService writingExerciseGradingService) {

        this.exerciseOptionRepository =
                exerciseOptionRepository;

        this.writingExerciseGradingService =
                writingExerciseGradingService;
    }

    @Override
    public ExerciseGradeResult grade(
            Exercise exercise,
            String answer) {

        if (answer == null ||
                answer.isBlank()) {

            throw new IllegalArgumentException(
                    "Exercise answer cannot be empty"
            );
        }

        String type =
                exercise.getType();

        if (type == null) {

            throw new IllegalStateException(
                    "Exercise type is missing"
            );
        }

        return switch (type.toUpperCase()) {

            case "FILL_BLANK" ->
                    gradeFillBlank(
                            exercise,
                            answer
                    );

            case "MULTIPLE_CHOICE" ->
                    gradeMultipleChoice(
                            exercise,
                            answer
                    );

            case "WRITING" ->
                    writingExerciseGradingService
                            .grade(
                                    exercise,
                                    answer
                            );

            default ->
                    throw new IllegalArgumentException(
                            "Unsupported exercise type: "
                                    + type
                    );
        };
    }

    private ExerciseGradeResult gradeFillBlank(
            Exercise exercise,
            String answer) {

        String expected =
                exercise.getCorrect_answer();

        if (expected == null ||
                expected.isBlank()) {

            throw new IllegalStateException(
                    "FILL_BLANK exercise has no correct_answer"
            );
        }

        boolean correct =
                normalize(answer)
                        .equals(
                                normalize(expected)
                        );

        BigDecimal score =
                correct
                        ? BigDecimal.valueOf(
                                exercise.getPoints()
                        )
                        : BigDecimal.ZERO;

        String feedback =
                correct
                        ? "Correct answer."
                        : "Incorrect answer. Expected answer: "
                                + expected;

        return new ExerciseGradeResult(
                correct,
                score,
                feedback
        );
    }

    private ExerciseGradeResult gradeMultipleChoice(
            Exercise exercise,
            String answer) {

        List<ExerciseOption> options =
                exerciseOptionRepository
                        .findByExercise(
                                exercise.getId()
                        );

        if (options.isEmpty()) {

            throw new IllegalStateException(
                    "MULTIPLE_CHOICE exercise has no options"
            );
        }

        ExerciseOption selected =
                options.stream()
                        .filter(option ->
                                normalize(
                                        option.getOption_text()
                                ).equals(
                                        normalize(answer)
                                )
                        )
                        .findFirst()
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Submitted answer is not a valid option"
                                )
                        );

        boolean correct =
                Boolean.TRUE.equals(
                        selected.getIs_correct()
                );

        BigDecimal score =
                correct
                        ? BigDecimal.valueOf(
                                exercise.getPoints()
                        )
                        : BigDecimal.ZERO;

        return new ExerciseGradeResult(
                correct,
                score,
                correct
                        ? "Correct answer."
                        : "Incorrect answer."
        );
    }

    private String normalize(
            String value) {

        return value
                .trim()
                .replaceAll("\\s+", " ")
                .toLowerCase();
    }
}