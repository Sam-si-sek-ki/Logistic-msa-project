package com.sparta.logistics.hub.domain.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedModel;

@Getter
@ToString
public class HubTransferPage extends PagedModel<HubTransfer> {

    public HubTransferPage(Page<HubTransfer> hubTransferPage) {
        super(
                new PageImpl<>(
                        hubTransferPage.getContent(),
                        hubTransferPage.getPageable(),
                        hubTransferPage.getTotalElements()
                )
        );
    }
}