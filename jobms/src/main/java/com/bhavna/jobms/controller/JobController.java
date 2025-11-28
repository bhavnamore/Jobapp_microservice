package com.bhavna.jobms.controller;
import com.bhavna.jobms.dto.JobDTO;
import com.bhavna.jobms.model.Job;
import com.bhavna.jobms.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("jobs")
public class JobController
{
    @Autowired
    public JobService service;

    @GetMapping("/getJobs")
    public ResponseEntity<List<JobDTO>> getJobs()
    {
        return new ResponseEntity<>(service.takeJobs(), HttpStatus.OK);
    }

    @PostMapping("/sendJob")
    public ResponseEntity<String> sendJob(@RequestBody Job job)
    {
        service.giveJob(job);
        return new ResponseEntity<>("Job added successfully",HttpStatus.CREATED);
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable int id)
    {
        return new ResponseEntity<>(service.getById(id),HttpStatus.OK);
    }

    @PutMapping("/job/update/{id}")
    public ResponseEntity<String> updateJob(@PathVariable int id,@RequestBody Job job)
    {
        if(service.updJob(id,job))
        {
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/job/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable int id)
    {
        if(service.delJob(id))
        {
            return new ResponseEntity<>("Job deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("Job not found",HttpStatus.NOT_FOUND);
    }
    @GetMapping("/getJobsList/{id}")
    public ResponseEntity<List<Job>> getJobsList(@PathVariable int id)
    {
        List<Job> jobs=service.getJobsByCompanyId(id);
        return new ResponseEntity<>(jobs,HttpStatus.OK);
    }
}
