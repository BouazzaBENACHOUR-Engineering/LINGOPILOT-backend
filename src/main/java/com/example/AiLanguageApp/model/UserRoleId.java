package com.example.AiLanguageApp.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserRoleId implements Serializable {

    private Long user_id;
    private Long role_id;

    public UserRoleId() {
    }

    public UserRoleId(Long user_id, Long role_id) {
        this.user_id = user_id;
        this.role_id = role_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof UserRoleId)) return false;

        UserRoleId that = (UserRoleId) o;

        return Objects.equals(user_id, that.user_id)
                && Objects.equals(role_id, that.role_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, role_id);
    }
}