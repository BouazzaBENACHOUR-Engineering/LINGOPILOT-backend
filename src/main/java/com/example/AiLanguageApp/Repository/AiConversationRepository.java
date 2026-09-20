package com.example.AiLanguageApp.Repository;

import com.example.AiLanguageApp.model.AiConversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AiConversationRepository extends JpaRepository<AiConversation, Long> {
}