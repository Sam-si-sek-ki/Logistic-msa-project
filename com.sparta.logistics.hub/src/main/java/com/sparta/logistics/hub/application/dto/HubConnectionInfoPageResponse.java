package com.sparta.logistics.hub.application.dto;

import com.sparta.logistics.hub.domain.model.HubConnectionInfo;
import com.sparta.logistics.hub.domain.model.HubConnectionInfoPage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class HubConnectionInfoPageResponse {

    private HubConnectionInfoPage hubConnectionInfoPage;

    public static HubConnectionInfoPageResponse of(Page<HubConnectionInfo> hubConnectionInfoPage) {
        return HubConnectionInfoPageResponse.builder()
                .hubConnectionInfoPage(new HubConnectionInfoPage(hubConnectionInfoPage))
                .build();
    }
}
