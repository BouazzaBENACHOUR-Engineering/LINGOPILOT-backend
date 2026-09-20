package com.example.AiLanguageApp.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.UserSkillProgressRequest;
import com.example.AiLanguageApp.DTO.Response.UserSkillProgressResponse;
import com.example.AiLanguageApp.Repository.UserSkillProgressRepository;
import com.example.AiLanguageApp.Service.Interface.UserSkillProgressService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.UserSkillProgress;

@Service
public class UserSkillProgressServiceImpl
        implements UserSkillProgressService {

    private final UserSkillProgressRepository
            userSkillProgressRepository;

    public UserSkillProgressServiceImpl(
            UserSkillProgressRepository userSkillProgressRepository) {

        this.userSkillProgressRepository =
                userSkillProgressRepository;
    }

    @Override
    public UserSkillProgressResponse create(
            UserSkillProgressRequest request) {

        UserSkillProgress userSkillProgress =
                new UserSkillProgress();

        UserSkillProgress savedUserSkillProgress =
                userSkillProgressRepository.save(
                        userSkillProgress
                );

        UserSkillProgressResponse response =
                new UserSkillProgressResponse();

        response.setId(savedUserSkillProgress.getId());

        return response;
    }

    @Override
    public UserSkillProgress save(
            UserSkillProgress userSkillProgress) {

        return userSkillProgressRepository.save(
                userSkillProgress
        );
    }

    @Override
    public UserSkillProgress update(
            UserSkillProgress userSkillProgress) {

        if (userSkillProgress.getId() == null ||
                !userSkillProgressRepository.existsById(
                        userSkillProgress.getId())) {

            throw new ResourceNotFoundException(
                    "User skill progress does not exist"
            );
        }

        return userSkillProgressRepository.save(
                userSkillProgress
        );
    }

    @Override
    public Optional<UserSkillProgress> findById(Long id) {

        UserSkillProgress userSkillProgress =
                userSkillProgressRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User skill progress does not exist"
                                )
                        );

        return Optional.of(userSkillProgress);
    }

    @Override
    public List<UserSkillProgress> findAll() {

        return userSkillProgressRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        UserSkillProgress userSkillProgress =
                userSkillProgressRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User skill progress does not exist"
                                )
                        );

        userSkillProgressRepository.delete(
                userSkillProgress
        );
    }
}