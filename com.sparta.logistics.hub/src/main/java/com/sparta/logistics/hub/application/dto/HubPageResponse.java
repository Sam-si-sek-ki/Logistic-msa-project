package com.sparta.logistics.hub.application.dto;

import com.sparta.logistics.hub.domain.model.Hub;
import com.sparta.logistics.hub.domain.model.HubPage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class HubPageResponse {

    private HubPage hubPage;

    public static HubPageResponse of(Page<Hub> hubPage) {
        return HubPageResponse.builder()
                .hubPage(new HubPage(hubPage))
                .build();
    }
}
