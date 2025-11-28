package com.bhavna.jobms.dto;

import com.bhavna.jobms.external.Company;
import com.bhavna.jobms.external.Review;
import lombok.Data;

import java.util.List;

@Data
public class JobDTO
{
    private int id;
    private String title;
    private String description;
    private int minSal;
    private int maxSal;
    private String location;
    private Long company;
    private Company CompanyDet;
    private List<Review> ReviewDet;
}
