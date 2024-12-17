package com.sparta.logistics.company.domain.repository;

import com.sparta.logistics.company.domain.model.Company;
import com.sparta.logistics.company.infrastructure.configuration.CompanySearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyRepositoryCustom {
  Page<Company> searchCompanies(CompanySearchCondition condition, Pageable pageable);
}
