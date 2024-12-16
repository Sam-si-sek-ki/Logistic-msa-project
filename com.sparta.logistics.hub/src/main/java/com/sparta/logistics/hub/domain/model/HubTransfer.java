package com.sparta.logistics.hub.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.logistics.hub.libs.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "p_hub_transfer")
public class HubTransfer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID hubTransferId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_hub_id", nullable = false)
    @JsonIgnore
    private Hub fromHub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_hub_id", nullable = false)
    @JsonIgnore
    private Hub toHub;

    private int duration;
    private int distance;
}

