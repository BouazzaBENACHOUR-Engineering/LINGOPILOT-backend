package com.example.AiLanguageApp.Service.Interface;

import com.example.AiLanguageApp.DTO.Request.RoleRequest;
import com.example.AiLanguageApp.DTO.Response.RoleResponse;
import com.example.AiLanguageApp.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Role save(Role role);

    Role update(Role role);

    Optional<Role> findById(Long id);

    List<Role> findAll();
    
    RoleResponse create(RoleRequest request);

    void deleteById(Long id);
}