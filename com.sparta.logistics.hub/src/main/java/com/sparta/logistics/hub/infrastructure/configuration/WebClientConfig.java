package com.sparta.logistics.hub.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
//                .baseUrl("https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving") // 기본 URL 설정
                .defaultHeader("X-NCP-APIGW-API-KEY-ID", "rj4di1c7he") // API Key ID 추가
                .defaultHeader("X-NCP-APIGW-API-KEY", "cw3ngtYGYxRXClxgvPncbPOIwrHvfQv813dtdrAT")       // API Key 추가
                .defaultHeader("Accept", "application/json") // 기본 헤더 설정
                .build();
    }

}
