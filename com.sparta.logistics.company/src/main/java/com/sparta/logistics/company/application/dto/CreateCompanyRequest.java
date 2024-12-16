package com.sparta.logistics.company.application.dto;

import com.sparta.logistics.company.domain.model.Company;
import com.sparta.logistics.company.domain.model.CompanyType;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import lombok.Getter;

@Getter
public class CreateCompanyRequest {

  @NotBlank
  private String companyName;
  @NotBlank
  private CompanyType companyType;
  @NotBlank
  private UUID hubId;
  @NotBlank
  private String companyMainAddress;
  @NotBlank
  private String companyDetailAddress;

  public Company toEntity() {
    return Company.builder()
        .companyName(this.companyName)
        .companyType(this.companyType)
        .hubId(this.hubId)
        .companyMainAddress(this.companyMainAddress)
        .companyDetailAddress(this.companyDetailAddress)
        .build();
  }
}
