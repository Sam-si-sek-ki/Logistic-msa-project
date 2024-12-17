package com.sparta.logistics.notification.presentation.dto;

import com.sparta.logistics.notification.domain.model.ServiceName;
import com.sparta.logistics.notification.domain.model.SlackMessageStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendSlackMessageRequest {

    @NotBlank(message = "Recipient ID는 필수입니다.")
    private String recipientId;

    @NotBlank(message = "Message 내용은 필수입니다.")
    private String message;

    private ServiceName serviceName;

    private UUID referenceId;

}
