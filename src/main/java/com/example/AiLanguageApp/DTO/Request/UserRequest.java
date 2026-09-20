package com.example.AiLanguageApp.DTO.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequest {

    @NotBlank(message = "email is required")
    @Email(message = "email must be valid")
    @Size(max = 255, message = "email must not exceed 255 characters")
    private String email;

    @NotBlank(message = "password is required")
    @Size(
        min = 8,
        max = 255,
        message = "password must contain between 8 and 255 characters"
    )
    private String password;

    @Size(max = 100, message = "first_name must not exceed 100 characters")
    private String first_name;

    @Size(max = 100, message = "last_name must not exceed 100 characters")
    private String last_name;

    private Long native_language_id;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Long getNative_language_id() {
        return native_language_id;
    }

    public void setNative_language_id(Long native_language_id) {
        this.native_language_id = native_language_id;
    }
}
