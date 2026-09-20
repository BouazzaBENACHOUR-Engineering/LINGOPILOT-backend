package com.example.AiLanguageApp.AI.Service.Implementation;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.AI.Client.LlmClient;
import com.example.AiLanguageApp.AI.Grading.ExerciseGradeResult;
import com.example.AiLanguageApp.AI.Grading.WritingGradingResult;
import com.example.AiLanguageApp.AI.Prompt.WritingGradingPromptBuilder;
import com.example.AiLanguageApp.AI.Service.WritingExerciseGradingService;
import com.example.AiLanguageApp.model.Exercise;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.json.JsonMapper;

@Service
public class WritingExerciseGradingServiceImpl
        implements WritingExerciseGradingService {

    private final LlmClient llmClient;

    private final JsonMapper jsonMapper;

    private final WritingGradingPromptBuilder writingGradingPromptBuilder;

    public WritingExerciseGradingServiceImpl(
            LlmClient llmClient,
            JsonMapper jsonMapper,
            WritingGradingPromptBuilder writingGradingPromptBuilder) {

        this.llmClient =
                llmClient;

        this.jsonMapper =
                jsonMapper;

        this.writingGradingPromptBuilder =
                writingGradingPromptBuilder;
    }

    @Override
    public ExerciseGradeResult grade(
            Exercise exercise,
            String answer) {

        if (answer == null ||
                answer.isBlank()) {

            throw new IllegalArgumentException(
                    "Writing answer cannot be empty"
            );
        }

        String prompt =
                writingGradingPromptBuilder
                        .build(
                                exercise,
                                answer
                        );

        String rawResponse =
                llmClient.generateResponse(
                        prompt
                );

        WritingGradingResult result =
                parseResult(
                        rawResponse
                );

        validateResult(
                result
        );

        BigDecimal maximumPoints =
                BigDecimal.valueOf(
                        exercise.getPoints()
                );

        BigDecimal exerciseScore =
                maximumPoints
                        .multiply(
                                result.getScore_percentage()
                        )
                        .divide(
                                new BigDecimal("100.00"),
                                2,
                                RoundingMode.HALF_UP
                        );

        return new ExerciseGradeResult(
                result.getIs_correct(),
                exerciseScore,
                result.getFeedback()
        );
    }

    private WritingGradingResult parseResult(
            String rawResponse) {

        if (rawResponse == null ||
                rawResponse.isBlank()) {

            throw new IllegalStateException(
                    "Empty AI writing grading response"
            );
        }

        String json =
                rawResponse.trim();

        if (json.startsWith("```json")) {

            json =
                    json.substring(7);

        } else if (json.startsWith("```")) {

            json =
                    json.substring(3);
        }

        if (json.endsWith("```")) {

            json =
                    json.substring(
                            0,
                            json.length() - 3
                    );
        }

        try {

            return jsonMapper.readValue(
                    json.trim(),
                    WritingGradingResult.class
            );

        } catch (JacksonException exception) {

            throw new IllegalStateException(
                    "Invalid AI writing grading response",
                    exception
            );
        }
    }

    private void validateResult(
            WritingGradingResult result) {

        if (result == null) {

            throw new IllegalStateException(
                    "AI writing grading result is missing"
            );
        }

        BigDecimal percentage =
                result.getScore_percentage();

        if (percentage == null ||
                percentage.compareTo(
                        BigDecimal.ZERO
                ) < 0 ||
                percentage.compareTo(
                        new BigDecimal("100.00")
                ) > 0) {

            throw new IllegalStateException(
                    "Invalid score_percentage"
            );
        }

        if (result.getIs_correct() == null) {

            throw new IllegalStateException(
                    "Invalid is_correct"
            );
        }

        if (result.getFeedback() == null ||
                result.getFeedback().isBlank()) {

            throw new IllegalStateException(
                    "Invalid writing feedback"
            );
        }
    }
}