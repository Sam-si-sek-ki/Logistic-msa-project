package com.sparta.logistics.company.presentation.controller;

import com.sparta.logistics.company.application.dto.CompanyResponse;
import com.sparta.logistics.company.application.dto.CreateCompanyRequest;
import com.sparta.logistics.company.application.dto.UpdateCompanyRequest;
import com.sparta.logistics.company.application.service.CompanyService;
import com.sparta.logistics.company.infrastructure.configuration.CompanySearchCondition;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

  private final CompanyService companyService;

  @PostMapping
  public ResponseEntity<CompanyResponse> createCompany(@RequestBody CreateCompanyRequest request) {
    return ResponseEntity.ok(companyService.createCompany(request));
  }

  @GetMapping("/{companyId}")
  public ResponseEntity<CompanyResponse> getCompany(@PathVariable UUID companyId) {
    log.info("Received GET request for company ID: {}", companyId);
    return ResponseEntity.ok(companyService.getCompany(companyId));
  }

  @PutMapping("/{companyId}")
  public ResponseEntity<CompanyResponse> updateCompany(
      @PathVariable UUID companyId,
      @RequestBody UpdateCompanyRequest request) {
    return ResponseEntity.ok(companyService.updateCompany(companyId, request));
  }

  @DeleteMapping("/{companyId}")
  public ResponseEntity<Void> deleteCompany(@PathVariable UUID companyId) {
    companyService.deleteCompany(companyId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<Page<CompanyResponse>> searchCompanies(
      @ModelAttribute CompanySearchCondition condition,
      @PageableDefault() Pageable pageable) {
    return ResponseEntity.ok(companyService.searchCompanies(condition, pageable));
  }
}
