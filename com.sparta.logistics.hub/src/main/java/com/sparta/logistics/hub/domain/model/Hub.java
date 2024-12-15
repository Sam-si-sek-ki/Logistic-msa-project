package com.sparta.logistics.hub.domain.model;

import com.sparta.logistics.hub.application.dto.HubRequestDto;
import com.sparta.logistics.hub.libs.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "p_hub")
public class Hub extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String hubId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private String address;

    private String addressDetail;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    public void update(String name, String address, String addressDetail, double latitude, double longitude) {
        validateCoordinates(latitude, longitude);

        if (name != null) this.name = name;
        if (address != null) this.address = address;
        if (addressDetail != null) this.addressDetail = addressDetail;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // 위도 및 경도 검증 메소드
    private void validateCoordinates(double latitude, double longitude) {
        if (latitude < -90.0 || latitude > 90.0) {
            throw new IllegalArgumentException("Invalid latitude value. It must be between -90.0 and 90.0.");
        }
        if (longitude < -180.0 || longitude > 180.0) {
            throw new IllegalArgumentException("Invalid longitude value. It must be between -180.0 and 180.0.");
        }
    }
}
