package com.example.AiLanguageApp.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.ModuleRequest;
import com.example.AiLanguageApp.DTO.Response.ModuleResponse;
import com.example.AiLanguageApp.Repository.ModuleRepository;
import com.example.AiLanguageApp.Service.Interface.ModuleService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.Module;

@Service
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;

    public ModuleServiceImpl(
            ModuleRepository moduleRepository) {

        this.moduleRepository = moduleRepository;
    }

    @Override
    public ModuleResponse create(
            ModuleRequest request) {

        Module module = new Module();

        Module savedModule =
                moduleRepository.save(module);

        ModuleResponse response =
                new ModuleResponse();

        response.setId(savedModule.getId());

        return response;
    }

    @Override
    public Module save(
            Module module) {

        return moduleRepository.save(module);
    }

    @Override
    public Module update(
            Module module) {

        if (module.getId() == null ||
                !moduleRepository.existsById(
                        module.getId())) {

            throw new ResourceNotFoundException(
                    "Module does not exist"
            );
        }

        return moduleRepository.save(module);
    }

    @Override
    public Optional<Module> findById(Long id) {

        Module module =
                moduleRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Module does not exist"
                                )
                        );

        return Optional.of(module);
    }

    @Override
    public List<Module> findAll() {

        return moduleRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        Module module =
                moduleRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Module does not exist"
                                )
                        );

        moduleRepository.delete(module);
    }
}