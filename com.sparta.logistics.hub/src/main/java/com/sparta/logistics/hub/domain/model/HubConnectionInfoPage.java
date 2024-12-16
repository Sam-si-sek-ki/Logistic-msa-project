package com.sparta.logistics.hub.domain.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedModel;

import java.util.stream.Collectors;

@Getter
@ToString
public class HubConnectionInfoPage extends PagedModel<HubConnectionInfoPage.HubConnectionInfoDto> {

    public HubConnectionInfoPage(Page<HubConnectionInfo> hubPage) {
        super(
                new PageImpl<>(
                        hubPage.getContent().stream()
                                .map(HubConnectionInfoDto::fromEntity)
                                .collect(Collectors.toList()),
                        hubPage.getPageable(),
                        hubPage.getTotalElements()
                )
        );
    }

    @Getter
    @ToString
    public static class HubConnectionInfoDto {
        private final String hubConnectionInfoId;
        private final String fromHubId;
        private final String toHubId;

        private HubConnectionInfoDto(String hubConnectionId, String fromHubId, String toHubId) {
            this.hubConnectionInfoId = hubConnectionId;
            this.fromHubId = fromHubId;
            this.toHubId = toHubId;
        }

        public static HubConnectionInfoDto fromEntity(HubConnectionInfo hubConnectionInfo) {
            return new HubConnectionInfoDto(
                    hubConnectionInfo.getHubConnectionInfoId().toString(),
                    hubConnectionInfo.getFromHub().getHubId().toString(),
                    hubConnectionInfo.getToHub().getHubId().toString()
            );
        }
    }
}
