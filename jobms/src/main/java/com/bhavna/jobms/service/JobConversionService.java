package com.bhavna.jobms.service;

import com.bhavna.jobms.Feignclients.CompanyClient;
import com.bhavna.jobms.Feignclients.ReviewClient;
import com.bhavna.jobms.dto.JobDTO;
import com.bhavna.jobms.external.Company;
import com.bhavna.jobms.external.Review;
import com.bhavna.jobms.mapper.JobDtoMapper;
import com.bhavna.jobms.model.Job;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobConversionService {

    @Autowired
    private CompanyClient companyclient;

    @Autowired
    private ReviewClient reviewclient;

    // 1. THE ANNOTATION IS HERE
    //@CircuitBreaker(name = "companyBreaker",fallbackMethod = "companyBreakerFallback")
    int attempt=0;
//    @Retry(name = "companyBreaker",fallbackMethod = "companyBreakerFallback")
   // @RateLimiter(name = "companyBreaker" , fallbackMethod = "companyBreakerFallback")
    public JobDTO converttoJobWithCompanyDTO(Job job)
    {
       // System.out.println("Attemp:"+ ++attempt);
        Company company = companyclient.getCompany(job.getCompany());
        List<Review> reviews = reviewclient.getReview(job.getCompany());

        return JobDtoMapper.jobMapper(job, company, reviews);
    }

    public JobDTO  companyBreakerFallback(Job job,Exception e)
    {
        System.out.println("Fallback triggered for "+job.getId()+" due to "+e.getMessage());
        Company company=new Company();
        company.setId(job.getCompany());
        company.setName("Company N/A(Service down)");
        company.setDescription("Company details are currently unavailable.");
        List<Review> reviews=new ArrayList<>();
        return JobDtoMapper.jobMapper(job,company,reviews);
    }
}