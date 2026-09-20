package com.example.AiLanguageApp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.AiLanguageApp.model.ConversationFeedback;

public interface ConversationFeedbackRepository extends JpaRepository<ConversationFeedback, Long> {

    @Query("""
            SELECT f
            FROM ConversationFeedback f
            WHERE f.message_id.id = :messageId
            """)
    Optional<ConversationFeedback> findByMessageId(
            @Param("messageId") Long messageId
    );
}