package com.sparta.logistics.delivery.infrastructure.client.company;

import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "company-service")
public interface CompanyServiceClient {

  @GetMapping("/companies/{companyId}")
  CompanyClientResponse getCompany(@PathVariable("companyId") UUID companyId,
      @RequestHeader(value = "X-Username", required = false) String username
  );
}
