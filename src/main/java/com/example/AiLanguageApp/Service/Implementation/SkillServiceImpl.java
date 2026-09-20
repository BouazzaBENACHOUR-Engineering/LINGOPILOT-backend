package com.example.AiLanguageApp.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.SkillRequest;
import com.example.AiLanguageApp.DTO.Response.SkillResponse;
import com.example.AiLanguageApp.Repository.SkillRepository;
import com.example.AiLanguageApp.Service.Interface.SkillService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.Skill;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(
            SkillRepository skillRepository) {

        this.skillRepository = skillRepository;
    }

    @Override
    public SkillResponse create(SkillRequest request) {

        Skill skill = new Skill();

        Skill savedSkill = skillRepository.save(skill);

        SkillResponse response = new SkillResponse();

        response.setId(savedSkill.getId());

        return response;
    }

    @Override
    public Skill save(Skill skill) {

        return skillRepository.save(skill);
    }

    @Override
    public Skill update(Skill skill) {

        if (skill.getId() == null ||
                !skillRepository.existsById(skill.getId())) {

            throw new ResourceNotFoundException(
                    "Skill does not exist"
            );
        }

        return skillRepository.save(skill);
    }

    @Override
    public Optional<Skill> findById(Long id) {

        Skill skill =
                skillRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Skill does not exist"
                                )
                        );

        return Optional.of(skill);
    }

    @Override
    public List<Skill> findAll() {

        return skillRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        Skill skill =
                skillRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Skill does not exist"
                                )
                        );

        skillRepository.delete(skill);
    }
}