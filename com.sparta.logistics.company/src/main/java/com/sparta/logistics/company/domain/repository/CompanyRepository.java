package com.sparta.logistics.company.domain.repository;

import com.sparta.logistics.company.domain.model.Company;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends JpaRepository<Company, UUID>, CompanyRepositoryCustom {

  @Query("SELECT c FROM Company c WHERE c.companyId = :companyId AND c.isDeleted = false")
  Optional<Company> findByCompanyId(@Param("companyId") UUID companyId);
}
