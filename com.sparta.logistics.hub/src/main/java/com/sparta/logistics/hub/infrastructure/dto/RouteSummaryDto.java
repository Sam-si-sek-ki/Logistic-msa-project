package com.sparta.logistics.hub.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 길찾기 요약 정보 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RouteSummaryDto {
    private int code;
    private String message;
    private LocalDateTime currentDateTime;
    private Summary summary;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Summary {
        private Location start;
        private Location goal;
        private long distance;
        private long duration;
        private LocalDateTime departureTime;
        private List<List<Double>> bbox;
        private int tollFare;
        private int taxiFare;
        private int fuelPrice;

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Location {
            private double latitude;
            private double longitude;
        }
    }
}
