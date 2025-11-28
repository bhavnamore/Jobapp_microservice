package com.bhavna.jobms.mapper;

import com.bhavna.jobms.dto.JobDTO;
import com.bhavna.jobms.external.Company;
import com.bhavna.jobms.external.Review;
import com.bhavna.jobms.model.Job;

import java.util.List;

public class JobDtoMapper
{
    public static JobDTO jobMapper(Job job, Company company, List<Review> reviews)
    {
        JobDTO jobwithcompanydto=new JobDTO();
        jobwithcompanydto.setId(job.getId());
        jobwithcompanydto.setLocation(job.getLocation());
        jobwithcompanydto.setTitle(job.getLocation());
        jobwithcompanydto.setDescription(job.getDescription());
        jobwithcompanydto.setMaxSal(job.getMaxSal());
        jobwithcompanydto.setMinSal(job.getMinSal());
        jobwithcompanydto.setCompany(job.getCompany());
        jobwithcompanydto.setCompanyDet(company);
        jobwithcompanydto.setReviewDet(reviews);
        return jobwithcompanydto;
    }
}
