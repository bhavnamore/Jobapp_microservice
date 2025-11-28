package com.bhavna.companyms.controller;
import com.bhavna.companyms.model.Company;
import com.bhavna.companyms.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController
{
    @Autowired
    private CompanyService service;
    @GetMapping("/getAllCompany")
    public ResponseEntity<List<Company>> getAllCompanies()
    {
        return new ResponseEntity<>(service.getAllcompanies(), HttpStatus.OK);
    }
    @PostMapping("/saveCompany")
    public ResponseEntity<String> saveCompany(@RequestBody Company company)
    {
        service.saveComp(company);
        return new ResponseEntity<>("Company saved successfully",HttpStatus.CREATED);
    }
    @PutMapping("/updateCompany/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,@RequestBody Company company)
    {
        if(service.updateCom(id,company))
        {
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        }
        return new ResponseEntity<>("Not present",HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/deleteCompany/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id)
    {
        if(service.deleteCom(id))
        {
            return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getCompanyById/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id)
    {
        return new ResponseEntity<>(service.getCompanyByid(id),HttpStatus.OK);
    }
}
