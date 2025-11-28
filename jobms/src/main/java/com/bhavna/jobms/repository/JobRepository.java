package com.bhavna.jobms.repository;
import com.bhavna.jobms.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Integer>
{
    List<Job> findCompanyById(int id);
}
