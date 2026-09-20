package com.example.AiLanguageApp.Repository;

import com.example.AiLanguageApp.model.AiMessage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AiMessageRepository extends JpaRepository<AiMessage, Long> {
	
	@Query("""
	        SELECT m
	        FROM AiMessage m
	        WHERE m.conversation_id.id = :conversationId
	        ORDER BY m.created_at ASC
	        """)
	List<AiMessage> findConversationHistory(
	        @Param("conversationId") Long conversationId
	);
	
}