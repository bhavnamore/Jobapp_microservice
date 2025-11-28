package com.bhavna.companyms.dto;

import lombok.Data;

@Data
public class ReviewMessage
{
    private int id;
    private String title;
    private String description;
    private double rating;
    private Long companyId;
}
