package com.example.AiLanguageApp.Service.Implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.AiLanguageApp.DTO.Request.UserRequest;
import com.example.AiLanguageApp.DTO.Request.UserUpdateRequest;
import com.example.AiLanguageApp.DTO.Response.UserResponse;
import com.example.AiLanguageApp.Mapper.UserMapper;
import com.example.AiLanguageApp.Repository.LanguageRepository;
import com.example.AiLanguageApp.Repository.RoleRepository;
import com.example.AiLanguageApp.Repository.UserRepository;
import com.example.AiLanguageApp.Repository.UserRoleRepository;
import com.example.AiLanguageApp.Service.Interface.UserService;
import com.example.AiLanguageApp.exception.DuplicateResourceException;
import com.example.AiLanguageApp.exception.ResourceNotFoundException;
import com.example.AiLanguageApp.model.Language;
import com.example.AiLanguageApp.model.Role;
import com.example.AiLanguageApp.model.User;
import com.example.AiLanguageApp.model.UserRole;
import com.example.AiLanguageApp.model.UserRoleId;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LanguageRepository languageRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(
            UserRepository userRepository,
            LanguageRepository languageRepository,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository,
            UserRoleRepository userRoleRepository,
            UserMapper userMapper) {

        this.userRepository = userRepository;
        this.languageRepository = languageRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse create(UserRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {

            throw new DuplicateResourceException(
                    "Email already exists"
            );
        }

        User user = new User();

        user.setEmail(request.getEmail());

        user.setPassword_hash(
                passwordEncoder.encode(request.getPassword())
        );

        user.setFirst_name(request.getFirst_name());
        user.setLast_name(request.getLast_name());

        if (request.getNative_language_id() != null) {

            Language language = languageRepository
                    .findById(request.getNative_language_id())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Native language does not exist"
                            )
                    );

            user.setNative_language_id(language);
        }

        user.setStatus("ACTIVE");

        LocalDateTime now = LocalDateTime.now();

        user.setCreated_at(now);
        user.setUpdated_at(now);

        User savedUser = userRepository.save(user);

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "USER role does not exist"
                        )
                );

        UserRole userRoleAssignment = new UserRole();

        userRoleAssignment.setId(
                new UserRoleId(
                        savedUser.getId(),
                        userRole.getId()
                )
        );

        userRoleAssignment.setUser(savedUser);
        userRoleAssignment.setRole(userRole);

        userRoleRepository.save(userRoleAssignment);

        return userMapper.toResponse(savedUser);
    }

    @Override
    public User save(User user) {

        return userRepository.save(user);
    }

    @Override
    public UserResponse update(
            Long id,
            UserUpdateRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User does not exist"
                        )
                );

        if (request.getEmail() != null &&
                !request.getEmail().equals(user.getEmail())) {

            if (userRepository.findByEmail(request.getEmail()).isPresent()) {

                throw new DuplicateResourceException(
                        "Email already exists"
                );
            }

            user.setEmail(request.getEmail());
        }

        if (request.getFirst_name() != null) {

            user.setFirst_name(request.getFirst_name());
        }

        if (request.getLast_name() != null) {

            user.setLast_name(request.getLast_name());
        }

        if (request.getNative_language_id() != null) {

            Language language = languageRepository
                    .findById(request.getNative_language_id())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Native language does not exist"
                            )
                    );

            user.setNative_language_id(language);
        }

        user.setUpdated_at(LocalDateTime.now());

        User updatedUser = userRepository.save(user);

        return userMapper.toResponse(updatedUser);
    }

    @Override
    public UserResponse findById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User does not exist"
                        )
                );

        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> findAll() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public void deleteById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User does not exist"
                        )
                );

        userRepository.delete(user);
    }
}