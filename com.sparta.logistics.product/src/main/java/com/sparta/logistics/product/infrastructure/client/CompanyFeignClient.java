package com.sparta.logistics.product.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "company-service", url = "http://company-service")
public class CompanyFeignClient {

    // 업체 존재 여부 확인
    @GetMapping("/api/companies/exist")
    boolean isCompanyExist(@RequestParam("companyId") Long companyId) {
        return false;
    }
}
