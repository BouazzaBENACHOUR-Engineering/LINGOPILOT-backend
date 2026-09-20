package com.example.AiLanguageApp.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.LevelRequest;
import com.example.AiLanguageApp.DTO.Response.LevelResponse;
import com.example.AiLanguageApp.Repository.LevelRepository;
import com.example.AiLanguageApp.Service.Interface.LevelService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.Level;

@Service
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;

    public LevelServiceImpl(
            LevelRepository levelRepository) {

        this.levelRepository = levelRepository;
    }

    @Override
    public LevelResponse create(
            LevelRequest request) {

        Level level = new Level();

        Level savedLevel =
                levelRepository.save(level);

        LevelResponse response =
                new LevelResponse();

        response.setId(savedLevel.getId());

        return response;
    }

    @Override
    public Level save(
            Level level) {

        return levelRepository.save(level);
    }

    @Override
    public Level update(
            Level level) {

        if (level.getId() == null ||
                !levelRepository.existsById(
                        level.getId())) {

            throw new ResourceNotFoundException(
                    "Level does not exist"
            );
        }

        return levelRepository.save(level);
    }

    @Override
    public Optional<Level> findById(Long id) {

        Level level =
                levelRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Level does not exist"
                                )
                        );

        return Optional.of(level);
    }

    @Override
    public List<Level> findAll() {

        return levelRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        Level level =
                levelRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Level does not exist"
                                )
                        );

        levelRepository.delete(level);
    }
}