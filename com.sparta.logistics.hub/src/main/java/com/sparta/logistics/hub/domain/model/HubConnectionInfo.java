package com.sparta.logistics.hub.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@Entity(name = "p_hub_connection_info")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HubConnectionInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID hubConnectionInfoId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "from_hub_id", nullable = false)
    private Hub fromHub;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "to_hub_id", nullable = false)
    private Hub toHub;
}
