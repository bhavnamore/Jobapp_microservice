package com.bhavna.companyms.service;

import com.bhavna.companyms.clients.ReviewClient;
import com.bhavna.companyms.dto.ReviewMessage;
import com.bhavna.companyms.model.Company;
import com.bhavna.companyms.repository.CompanyRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService
{
    @Autowired
    private CompanyRepository repo;

    @Autowired
    private ReviewClient reviewclient;

    public List<Company> getAllcompanies()
    {
        return repo.findAll();
    }

    public void saveComp(Company company)
    {
        repo.save(company);
    }

    public boolean updateCom(Long id, Company company)
    {
        Optional<Company> comp=repo.findById(id);
        if(comp.isPresent())
        {
            Company existing=comp.get();
            existing.setDescription(company.getDescription());
            existing.setName(company.getName());
            return true;
        }
        return false;
    }
    public boolean deleteCom(Long id)
    {
        Optional<Company> comp=repo.findById(id);
        if(comp.isPresent())
        {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public Company getCompanyByid(Long id)
    {
        Company comp=repo.findById(id).orElse(null);
        return comp;
    }

    public void updateCompanyRating(ReviewMessage reviewmessage)
    {
        System.out.println(reviewmessage.getDescription());
        Company company=repo.findById(reviewmessage.getCompanyId()).orElse(null);
        Double rating=reviewclient.getCompanyByRating(company.getId());
        company.setRating(rating);
        repo.save(company);
    }

//    public Company getCompanyById(int id)
//    {
//        return repo.findById(id).orElse(null);
//    }
}
