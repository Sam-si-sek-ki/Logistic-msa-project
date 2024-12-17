package com.sparta.logistics.order.infrastructure.client;

import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "company-service")
public interface CompanyServiceClient {

    @GetMapping("/companies/validate")
    ResponseEntity<Void> checkCompaniesExist(
        @RequestParam UUID supplierCompanyId,
        @RequestParam UUID receiverCompanyId
    );
}
