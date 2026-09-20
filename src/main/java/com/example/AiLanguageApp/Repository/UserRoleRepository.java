package com.example.AiLanguageApp.Repository;

import com.example.AiLanguageApp.model.UserRole;
import com.example.AiLanguageApp.model.UserRoleId;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
	
	List<UserRole> findByUser_Id(Long user_id);
	
}