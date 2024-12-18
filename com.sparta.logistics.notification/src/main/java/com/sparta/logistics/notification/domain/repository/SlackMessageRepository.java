package com.sparta.logistics.notification.domain.repository;

import com.sparta.logistics.notification.domain.model.SlackMessage;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlackMessageRepository extends JpaRepository<SlackMessage, UUID> {

    Optional<SlackMessage> findBySlackMessageIdAndIsDeletedFalse(UUID slackMessageId);
}
