package com.sparta.logistics.company.domain.model;

import com.sparta.logistics.company.libs.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "p_company")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "company_id", columnDefinition = "uuid")
  private UUID companyId;

  @Column(name = "company_name", nullable = false)
  private String companyName;

  @Enumerated(EnumType.STRING)
  @Column(name = "company_type", nullable = false)
  private CompanyType companyType;

  @Column(name = "hub_id", nullable = false, columnDefinition = "uuid")
  private UUID hubId;

  @Column(name = "company_main_address", length = 100, nullable = false)
  private String companyMainAddress;

  @Column(name = "company_detail_address", length = 100, nullable = false)
  private String companyDetailAddress;

  @Builder
  private Company(String companyName, CompanyType companyType, UUID hubId,
      String companyMainAddress, String companyDetailAddress) {
    this.companyName = companyName;
    this.companyType = companyType;
    this.hubId = hubId;
    this.companyMainAddress = companyMainAddress;
    this.companyDetailAddress = companyDetailAddress;
  }

  public void update(String companyName,
      String companyMainAddress, String companyDetailAddress) {
    this.companyName = companyName;
    this.companyMainAddress = companyMainAddress;
    this.companyDetailAddress = companyDetailAddress;
  }
}
