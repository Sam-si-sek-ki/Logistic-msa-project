package com.sparta.logistics.notification.domain.model;

import com.sparta.logistics.notification.libs.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "p_slack_message")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class SlackMessage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, unique = true, updatable = false)
    private UUID slackMessageId;

    @Column(nullable = false, length = 255)
    private String recipientId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SlackMessageStatus status;

    @Enumerated(EnumType.STRING)
    private ServiceName serviceName;

    private UUID referenceId;

    private Integer retryCount = 0; // 초기값 설정

    private String slackTimestamp;

    // 엔티티 저장 전 초기값 설정
    @PrePersist
    public void prePersist() {
        if (this.retryCount == null) {
            this.retryCount = 0;
        }
    }

     // 슬랙 메시지 생성 메서드
     public static SlackMessage create(
             String recipientId,
             String message,
             SlackMessageStatus status,
             ServiceName serviceName,
             UUID referenceId
     ) {
         return SlackMessage.builder()
                 .recipientId(recipientId)
                 .message(message)
                 .status(status)
                 .serviceName(serviceName)
                 .referenceId(referenceId)
                 .build();
    }

    // 슬랙 메시지 수정 메서드
    public void update(
            String recipientId,
            String message,
            SlackMessageStatus status,
            ServiceName serviceName,
            UUID referenceId,
            Integer retryCount,
            String slackTimestamp
    ) {
        if (recipientId != null) this.recipientId = recipientId;
        if (message != null) this.message = message;
        if (status != null) this.status = status;
        if (serviceName != null) this.serviceName = serviceName;
        if (referenceId != null) this.referenceId = referenceId;
        if (retryCount != null) this.retryCount = retryCount;
        if (slackTimestamp != null) this.slackTimestamp = slackTimestamp;
    }

    // Retry 횟수 증가 메서드
    public void updateRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }
}
