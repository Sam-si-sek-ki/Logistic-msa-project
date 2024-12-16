package com.sparta.logistics.hub.domain.model;

import com.sparta.logistics.hub.application.dto.HubTransferResponseDto;
import com.sparta.logistics.hub.domain.mapper.HubTransferMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Getter
@ToString
public class HubTransferPage extends PagedModel<HubTransferResponseDto> {

    public HubTransferPage(Page<HubTransfer> hubTransferPage) {
        super(
                new PageImpl<>(
                        hubTransferPage.getContent().stream()
                                .map(HubTransferMapper::toDto)
                                .collect(Collectors.toList()),
                        hubTransferPage.getPageable(),
                        hubTransferPage.getTotalElements()
                )
        );
    }
}