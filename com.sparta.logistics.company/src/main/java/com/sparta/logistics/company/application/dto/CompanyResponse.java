package com.sparta.logistics.company.application.dto;

import com.sparta.logistics.company.domain.model.Company;
import com.sparta.logistics.company.domain.model.CompanyType;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyResponse {

  private UUID companyId;
  private String companyName;
  private CompanyType companyType;
  private UUID hubId;
  private String companyMainAddress;
  private String companyDetailAddress;
  private LocalDateTime createdAt;
  private String createdBy;

  public static CompanyResponse fromEntity(Company company) {
    return CompanyResponse.builder()
        .companyId(company.getCompanyId())
        .companyName(company.getCompanyName())
        .companyType(company.getCompanyType())
        .hubId(company.getHubId())
        .companyMainAddress(company.getCompanyMainAddress())
        .companyDetailAddress(company.getCompanyDetailAddress())
        .build();
  }
}
