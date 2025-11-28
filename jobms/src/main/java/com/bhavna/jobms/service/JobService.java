package com.bhavna.jobms.service;
import com.bhavna.jobms.Feignclients.CompanyClient;
import com.bhavna.jobms.Feignclients.ReviewClient;
import com.bhavna.jobms.dto.JobDTO;
import com.bhavna.jobms.external.Company;
import com.bhavna.jobms.external.Review;
import com.bhavna.jobms.mapper.JobDtoMapper;
import com.bhavna.jobms.model.Job;
import com.bhavna.jobms.repository.JobRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    // 1. Inject the repository
    @Autowired
    private JobRepository jobRepo;

    @Autowired
    RestTemplate resttemplate;

    @Autowired
    private JobConversionService jobConversionService;
    // No more in-memory list!
    // List<Job> joblist = new ArrayList<>();

    // 2. Use repository methods
    public List<JobDTO> takeJobs()
    {
        List<Job> jobs=jobRepo.findAll();
        List<JobDTO> jobwithcompanydto=new ArrayList<>();
        //RestTemplate resttemplate=new RestTemplate();
        for(Job job:jobs)
        {
            jobwithcompanydto.add(jobConversionService.converttoJobWithCompanyDTO(job));
        }
        return jobwithcompanydto;
    }

    public void giveJob(Job job) {
        // The save method handles both create and update.
        // JPA will automatically generate and set the ID.
        jobRepo.save(job);
    }

//    @CircuitBreaker(name = "companyBreaker")
//    public JobDTO converttoJobWithCompanyDTO(Job job)
//    {
//        //Company company=resttemplate.getForObject("http://COMPANYMS-SERVICE/company/getCompanyById/"+job.getCompany(),Company.class);
//        Company company=companyclient.getCompany(job.getCompany());
//       ResponseEntity<List<Review>> reviewresponse=resttemplate.exchange("http://REVIEWMS-SERVICE/companies/"+company.getId()+"/reviews",
//             HttpMethod.GET,
//             null,
//                new ParameterizedTypeReference<List<Review>>() {
//                });
//        List<Review> reviews=reviewresponse.getBody();
//        List<Review> reviews=reviewclient.getReview(job.getCompany());
//        JobDTO jobwithcompanydto=JobDtoMapper.jobMapper(job,company,reviews);
//        return jobwithcompanydto;
//    }
    public JobDTO getById(int id) {
        // findById returns an Optional to handle cases where the job might not exist
        Job job= jobRepo.findById(id).orElse(null);
        return jobConversionService.converttoJobWithCompanyDTO(job);
    }

    public boolean updJob(int id, Job updatedJob) {
        Optional<Job> optionalJob = jobRepo.findById(id);
        if (optionalJob.isPresent()) {
            Job existingJob = optionalJob.get();
            // Update the fields of the existing job
            existingJob.setTitle(updatedJob.getTitle());
            existingJob.setDescription(updatedJob.getDescription());
            existingJob.setMinSal(updatedJob.getMinSal());
            existingJob.setMaxSal(updatedJob.getMaxSal());
            existingJob.setLocation(updatedJob.getLocation());
            existingJob.setCompany(updatedJob.getCompany()); // Assuming you might update the company too

            // Save the updated job back to the database
            jobRepo.save(existingJob);
            return true;
        }
        return false;
    }

    public boolean delJob(int id) {
        if (jobRepo.existsById(id)) {
            jobRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Job> getJobsByCompanyId(int id)
    {
        return jobRepo.findCompanyById(id);
    }
}