package com.bhavna.jobms.Feignclients;

import com.bhavna.jobms.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "REVIEWMS-SERVICE")
public interface ReviewClient
{
    @GetMapping("/companies/reviews")
    List<Review> getReview(@RequestParam("companyid") Long companyid);
}
