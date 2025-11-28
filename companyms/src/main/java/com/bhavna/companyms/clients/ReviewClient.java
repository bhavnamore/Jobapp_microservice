package com.bhavna.companyms.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "REVIEWMS-SERVICE")
public interface ReviewClient
{
    @GetMapping("/companies/avgreviews")
    Double getCompanyByRating(@RequestParam("companyid") Long companyid);
}
