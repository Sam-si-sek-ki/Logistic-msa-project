package com.sparta.logistics.company.application.service;

import com.sparta.logistics.company.application.dto.CompanyResponse;
import com.sparta.logistics.company.application.dto.CreateCompanyRequest;
import com.sparta.logistics.company.application.dto.UpdateCompanyRequest;
import com.sparta.logistics.company.domain.model.Company;
import com.sparta.logistics.company.domain.repository.CompanyRepository;
import com.sparta.logistics.company.infrastructure.client.HubClientResponse;
import com.sparta.logistics.company.infrastructure.client.HubServiceClient;
import com.sparta.logistics.company.infrastructure.configuration.CompanySearchCondition;
import com.sparta.logistics.company.libs.exception.ErrorCode;
import com.sparta.logistics.company.libs.exception.GlobalException;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {

  private final CompanyRepository companyRepository;
  private final HubServiceClient hubServiceClient;

  @Transactional
  public CompanyResponse createCompany(CreateCompanyRequest request) {
    validateHubId(request.getHubId());
    Company company = request.toEntity();
    Company savedCompany = companyRepository.save(company);
    return CompanyResponse.fromEntity(savedCompany);
  }

  public CompanyResponse getCompany(UUID companyId) {
    Company company = findCompanyById(companyId);
    return CompanyResponse.fromEntity(company);
  }

  @Transactional
  public CompanyResponse updateCompany(UUID companyId, UpdateCompanyRequest request) {
    Company company = findCompanyById(companyId);

    company.update(
        request.getCompanyName(),
        request.getCompanyMainAddress(),
        request.getCompanyDetailAddress()
    );

    return CompanyResponse.fromEntity(company);
  }

  public Page<CompanyResponse> searchCompanies(CompanySearchCondition condition, Pageable pageable) {
    return companyRepository.searchCompanies(condition, pageable)
        .map(CompanyResponse::fromEntity);
  }

  @Transactional
  public void deleteCompany(UUID companyId) {
    Company company = findCompanyById(companyId);
    company.setDelete(LocalDateTime.now(), "SYSTEM");
  }

  private Company findCompanyById(UUID companyId) {
    return companyRepository.findByCompanyId(companyId)
        .orElseThrow(() -> new GlobalException(ErrorCode.COMPANY_NOT_FOUND));
  }

  private void validateHubId(UUID hubId) {
    ResponseEntity<HubClientResponse> hubResponse = hubServiceClient.getHub(hubId);
    if (!hubResponse.getStatusCode().is2xxSuccessful()) {
      throw new GlobalException(ErrorCode.HUB_NOT_FOUND);
    }
  }
}
