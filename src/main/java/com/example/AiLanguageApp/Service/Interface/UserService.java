package com.example.AiLanguageApp.Service.Interface;

import java.util.List;

import com.example.AiLanguageApp.DTO.Request.UserRequest;
import com.example.AiLanguageApp.DTO.Request.UserUpdateRequest;
import com.example.AiLanguageApp.DTO.Response.UserResponse;
import com.example.AiLanguageApp.model.User;

public interface UserService {

    UserResponse create(UserRequest request);

    User save(User user);

    UserResponse update(Long id, UserUpdateRequest request);

    UserResponse findById(Long id);

    List<UserResponse> findAll();

    void deleteById(Long id);
}