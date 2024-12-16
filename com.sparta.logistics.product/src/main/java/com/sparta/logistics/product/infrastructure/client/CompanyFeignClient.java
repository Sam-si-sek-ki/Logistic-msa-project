package com.sparta.logistics.product.infrastructure.client;

import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "company-service", url = "http://company-service")
public interface CompanyFeignClient {

    // todo : 업체 존재 여부 확인 -> hubId !=null 인지 확인
    @GetMapping("/companies/exist")
    boolean isCompanyExist(@RequestParam("companyId") UUID companyId, @RequestParam String userName,
        @RequestParam String userRole);
}
