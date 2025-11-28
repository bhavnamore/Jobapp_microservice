package com.bhavna.jobms.external;

import lombok.Data;

@Data
public class Review
{
    private int id;
    private String title;
    private String description;
    private double rating;
}
