package com.example.AiLanguageApp.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.RoleRequest;
import com.example.AiLanguageApp.DTO.Response.RoleResponse;
import com.example.AiLanguageApp.Repository.RoleRepository;
import com.example.AiLanguageApp.Service.Interface.RoleService;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.Role;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(
            RoleRepository roleRepository) {

        this.roleRepository = roleRepository;
    }

    @Override
    public RoleResponse create(RoleRequest request) {

        Role role = new Role();

        Role savedRole = roleRepository.save(role);

        RoleResponse response = new RoleResponse();

        response.setId(savedRole.getId());

        return response;
    }

    @Override
    public Role save(Role role) {

        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {

        if (role.getId() == null ||
                !roleRepository.existsById(role.getId())) {

            throw new ResourceNotFoundException(
                    "Role does not exist"
            );
        }

        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> findById(Long id) {

        Role role =
                roleRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Role does not exist"
                                )
                        );

        return Optional.of(role);
    }

    @Override
    public List<Role> findAll() {

        return roleRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

        Role role =
                roleRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Role does not exist"
                                )
                        );

        roleRepository.delete(role);
    }
}