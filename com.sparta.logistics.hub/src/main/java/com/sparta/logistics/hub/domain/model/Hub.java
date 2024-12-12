package com.sparta.logistics.hub.domain.model;

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
}
