package com.bhavna.jobms.Feignclients;

import com.bhavna.jobms.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANYMS-SERVICE")
public interface CompanyClient
{
    @GetMapping("/company/getCompanyById/{id}")
    Company getCompany(@PathVariable("id") Long id);
}

