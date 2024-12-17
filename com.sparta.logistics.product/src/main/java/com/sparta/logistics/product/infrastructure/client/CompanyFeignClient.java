package com.sparta.logistics.product.infrastructure.client;

import com.sparta.logistics.product.infrastructure.dto.CompanyFeignClientResponse;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "company-service")
public interface CompanyFeignClient {

    @GetMapping("/companies/{companyId}")
    ResponseEntity<CompanyFeignClientResponse> checkCompanyExistence(
        @RequestParam("companyId") UUID companyId
    );
}