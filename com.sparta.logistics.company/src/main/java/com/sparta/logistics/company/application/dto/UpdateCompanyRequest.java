package com.sparta.logistics.company.application.dto;

import com.sparta.logistics.company.domain.model.Company;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateCompanyRequest {
  @NotBlank
  private String companyName;
  @NotBlank
  private String companyMainAddress;
  @NotBlank
  private String companyDetailAddress;

  public UpdateCompanyRequest toEntity(Company company) {
    return UpdateCompanyRequest.builder()
        .companyName(this.companyName)
        .companyMainAddress(this.companyMainAddress)
        .companyDetailAddress(this.companyDetailAddress)
        .build();
  }
}
