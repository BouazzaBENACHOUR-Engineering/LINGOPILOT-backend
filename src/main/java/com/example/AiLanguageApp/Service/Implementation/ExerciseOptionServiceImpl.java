package com.example.AiLanguageApp.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.ExerciseOptionRequest;
import com.example.AiLanguageApp.DTO.Response.ExerciseOptionResponse;
import com.example.AiLanguageApp.Repository.ExerciseOptionRepository;
import com.example.AiLanguageApp.Service.Interface.ExerciseOptionService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.ExerciseOption;

@Service
public class ExerciseOptionServiceImpl implements ExerciseOptionService {

    private final ExerciseOptionRepository exerciseOptionRepository;

    public ExerciseOptionServiceImpl(
            ExerciseOptionRepository exerciseOptionRepository) {

        this.exerciseOptionRepository = exerciseOptionRepository;
    }

    @Override
    public ExerciseOptionResponse create(
            ExerciseOptionRequest request) {

        ExerciseOption exerciseOption = new ExerciseOption();

        ExerciseOption savedExerciseOption =
                exerciseOptionRepository.save(exerciseOption);

        ExerciseOptionResponse response =
                new ExerciseOptionResponse();

        response.setId(savedExerciseOption.getId());

        return response;
    }

    @Override
    public ExerciseOption save(
            ExerciseOption exerciseOption) {

        return exerciseOptionRepository.save(exerciseOption);
    }

    @Override
    public ExerciseOption update(
            ExerciseOption exerciseOption) {

        if (exerciseOption.getId() == null ||
                !exerciseOptionRepository.existsById(
                        exerciseOption.getId())) {

            throw new ResourceNotFoundException(
                    "Exercise option does not exist"
            );
        }

        return exerciseOptionRepository.save(exerciseOption);
    }

    @Override
    public Optional<ExerciseOption> findById(Long id) {

        ExerciseOption exerciseOption =
                exerciseOptionRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Exercise option does not exist"
                                )
                        );

        return Optional.of(exerciseOption);
    }

    @Override
    public List<ExerciseOption> findAll() {

        return exerciseOptionRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        ExerciseOption exerciseOption =
                exerciseOptionRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Exercise option does not exist"
                                )
                        );

        exerciseOptionRepository.delete(exerciseOption);
    }
}