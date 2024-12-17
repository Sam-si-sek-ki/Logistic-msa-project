package com.sparta.logistics.hub.application.dto;

import com.sparta.logistics.hub.domain.model.HubTransfer;
import com.sparta.logistics.hub.domain.model.HubTransferPage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class HubTransferPageResponse {

    private HubTransferPage hubTransferPage;

    public static HubTransferPageResponse of(Page<HubTransfer> hubTransferPage) {
        return HubTransferPageResponse.builder()
                .hubTransferPage(new HubTransferPage(hubTransferPage))
                .build();
    }
}
