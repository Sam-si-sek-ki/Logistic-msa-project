package com.sparta.logistics.delivery.infrastructure.client.dto;

import java.util.UUID;
import lombok.Getter;

@Getter
public class CompanyClientResponse {
  private UUID companyId;
  private String companyName;
  private String companyMainAddress;
  private String companyDetailAddress;
  private UUID hubId;
}
