package com.sparta.logistics.hub.domain.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedModel;

@Getter
@ToString
public class HubPage extends PagedModel<Hub> {

    public HubPage(Page<Hub> hubPage) {
        super(
                new PageImpl<>(
                        hubPage.getContent(),
                        hubPage.getPageable(),
                        hubPage.getTotalElements()
                )
        );
    }
}
