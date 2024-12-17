package com.sparta.logistics.notification.domain.model;

import com.sparta.logistics.notification.libs.model.BaseEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "p_slack_reservation")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class SlackReservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "slack_reservation_id", nullable = false, unique = true, updatable = false)
    private UUID slackReservationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slack_message_id", nullable = false)
    private SlackMessage slackMessage;

    @Column(name = "scheduled_at")
    private LocalDateTime scheduledAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SlackReservationStatus status;

    // 생성 메서드
    public static SlackReservation create(
            SlackMessage slackMessage,
            LocalDateTime scheduledAt,
            SlackReservationStatus status
    ) {
        return SlackReservation.builder()
                .slackMessage(slackMessage)
                .scheduledAt(scheduledAt)
                .status(status)
                .build();
    }

    // 상태 업데이트 메서드
    public void updateStatus(SlackReservationStatus status) {
        if (status != null) {
            this.status = status;
        }
    }

    // 예약 시간 업데이트 메서드
    public void updateScheduledAt(LocalDateTime scheduledAt) {
        if (scheduledAt != null) {
            this.scheduledAt = scheduledAt;
        }
    }
}
