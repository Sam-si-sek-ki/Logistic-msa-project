package com.sparta.logistics.hub.application.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.sparta.logistics.hub.application.dto.*;
import com.sparta.logistics.hub.domain.mapper.HubTransferMapper;
import com.sparta.logistics.hub.domain.model.Hub;
import com.sparta.logistics.hub.domain.model.HubConnectionInfo;
import com.sparta.logistics.hub.domain.model.HubTransfer;
import com.sparta.logistics.hub.domain.repository.HubConnectionInfoRepository;
import com.sparta.logistics.hub.domain.repository.HubRepository;
import com.sparta.logistics.hub.domain.repository.HubTransferRepository;
import com.sparta.logistics.hub.infrastructure.dto.RouteSummaryDto;
import com.sparta.logistics.hub.libs.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static com.sparta.logistics.hub.domain.model.QHubTransfer.hubTransfer;
import static com.sparta.logistics.hub.libs.exception.ErrorCode.HUB_TRANSFER_CREATE_FAIL;
import static com.sparta.logistics.hub.libs.exception.ErrorCode.HUB_TRANSFER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class HubTransferService {

    private final HubTransferRepository hubTransferRepository;
    private final HubRepository hubRepository;
    private final HubConnectionInfoRepository hubConnectionInfoRepository;
    private final HubTransferMapper hubTransferMapper;
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @Transactional(readOnly = true)
    public HubTransferResponseDto getHubTransfersById(UUID id) {
        return hubTransferMapper.toDto(
                hubTransferRepository.findById(id).orElseThrow( () -> new GlobalException(HUB_TRANSFER_NOT_FOUND))
        );
    }

    @Transactional(readOnly = true)
    public HubTransferPageResponse getHubTransfers(Predicate predicate, Pageable pageable) {
        BooleanBuilder bb = new BooleanBuilder(predicate);
        bb.and(hubTransfer.isDeleted.eq(false));

        Page<HubTransfer> hubPage = hubTransferRepository.findAll(bb, pageable);
        return HubTransferPageResponse.of(hubPage);
    }

    @Transactional
    public void createHubTransfer(HubTransferRequestDto request) {

        Hub fromHub = hubRepository.findById(UUID.fromString(request.fromHubId())).orElseThrow(() -> new GlobalException(HUB_TRANSFER_NOT_FOUND));
        Hub toHub = hubRepository.findById(UUID.fromString(request.toHubId())).orElseThrow(() -> new GlobalException(HUB_TRANSFER_NOT_FOUND));

        HubTransfer hubTransfer = hubTransferMapper.toEntity(request, fromHub, toHub);
        hubTransferRepository.save(hubTransfer);
    }

    @Transactional
    public void deleteHubTransfer(UUID id) {

        HubTransfer hubTransfer = hubTransferRepository.findById(id).orElseThrow(() -> new GlobalException(HUB_TRANSFER_NOT_FOUND));

        hubTransfer.setDelete(LocalDateTime.now(), "username");
    }

    public CreateTransferRouteResponseDto createTransferRoute(CreateTransferRouteRequestDto request) {

        HubTransfer hubTransfer = hubTransferRepository.findByFromHubHubIdAndToHubHubId(UUID.fromString(request.fromHubId()), UUID.fromString(request.toHubId()));

        CreateTransferRouteResponseDto dto = new CreateTransferRouteResponseDto(
                1,
                hubTransfer.getFromHub().getHubId().toString(),
                hubTransfer.getToHub().getHubId().toString(),
                hubTransfer.getDistance(),
                hubTransfer.getDuration()
        );

        return dto;
    }

    public List<String> dijkstra( String from , String to, List<Route> routes){

        return null;
    }

    @Transactional
    public void createHubTransfer2() {

        List<HubConnectionInfo> connectionInfos = hubConnectionInfoRepository.findAll();
        List<HubTransfer> hubTransfers = new ArrayList<>();
        int logCount = 0;
        Timestamp init = Timestamp.valueOf(LocalDateTime.now());

        for (HubConnectionInfo connectionInfo : connectionInfos) {

            String log = logCount == 0 ? "0.00%" :String.format("%.2f",(double)logCount / connectionInfos.size() * 100)+"%";
            System.out.println("🚥TransferDataInit : [" + log + "]");
            logCount++;

            long[] disAndDur = aiRequest(connectionInfo.getFromHub(), connectionInfo.getToHub());

            HubTransfer hubTransfer = HubTransfer.builder()
                    .fromHub(connectionInfo.getFromHub())
                    .toHub(connectionInfo.getToHub())
                    .distance(disAndDur[0])
                    .duration(disAndDur[1])
                    .build();

            hubTransfers.add(hubTransfer);
        }
        log.info("Total Initialization sec : {}", Timestamp.valueOf(LocalDateTime.now()).getTime() - init.getTime());

        hubTransferRepository.saveAll(hubTransfers);
    }

    public long[] aiRequest(Hub fromHub, Hub toHub){

        String goal = "goal=" + toHub.getLongitude() + "," + toHub.getLatitude();
        String start = "start=" + fromHub.getLongitude() + "," + fromHub.getLatitude();
        String uri = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving?"+ goal + "&" + start;

        String response = callNaverApi(uri).block();

        return getSummaryValues(response);
    }

    public Mono<String> callNaverApi(String uri){
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class);
    }

    public long[] getSummaryValues(String jsonResponse) {
        try {
            // JSON 문자열을 JsonNode로 변환
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            // summary 노드에 접근
            JsonNode summaryNode = rootNode
                    .path("route")
                    .path("traoptimal")
                    .get(0) // traoptimal 배열의 첫 번째 요소
                    .path("summary");

            // distance와 duration 값 추출
            long distance = summaryNode.path("distance").asLong();
            long duration = summaryNode.path("duration").asLong();

            // 결과 출력
            return new long[]{distance, duration};

        } catch (Exception e) {
            throw new GlobalException(HUB_TRANSFER_CREATE_FAIL);
        }
    }
}