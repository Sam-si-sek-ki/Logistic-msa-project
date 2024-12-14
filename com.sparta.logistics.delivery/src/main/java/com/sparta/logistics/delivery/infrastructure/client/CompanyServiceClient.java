package com.sparta.logistics.delivery.infrastructure.client;

import com.sparta.logistics.delivery.infrastructure.client.dto.CompanyClientResponse;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "company-service")
public interface CompanyServiceClient {

  @GetMapping("/companies/{companyId}")
  CompanyClientResponse getCompany(@PathVariable UUID companyId);
}
