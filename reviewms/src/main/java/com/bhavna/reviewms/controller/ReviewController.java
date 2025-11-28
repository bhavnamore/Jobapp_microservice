package com.bhavna.reviewms.controller;
import com.bhavna.reviewms.model.Review;
import com.bhavna.reviewms.repository.ReviewRepository;
import com.bhavna.reviewms.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class ReviewController
{
    @Autowired
    private ReviewService service;

    @Autowired
    private ReviewRepository repo;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam("companyid") Long companyid)
    {
        return new ResponseEntity<>(service.getAllreviews(companyid), HttpStatus.OK);
    }

    @PostMapping("/giveReviews")
    public ResponseEntity<String> giveReviews(@RequestParam("compid") Long compid,@RequestBody Review review)
    {
        if(service.takeReview(compid,review))
        {
            return new ResponseEntity<>("Review Saved Successfully",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Review not saved",HttpStatus.BAD_REQUEST);
    }

    @PutMapping("{compid}/updateReviews/{rev_id}")
    public ResponseEntity<String> updateReviews(@PathVariable Long compid,@PathVariable int rev_id,@RequestBody Review review)
    {
        if(service.updReview(compid,rev_id,review))
        {
            return new ResponseEntity<>("Updated successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Not updated",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("{compid}/deleteReview/{rev_id}")
    public ResponseEntity<String> deleteReviews(@PathVariable Long compid,@PathVariable int rev_id)
    {
        if(service.delReview(compid,rev_id))
        {
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Deleted",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/avgreviews")
    public Double averageReviews(@RequestParam("companyid") Long companyid)
    {
        List<Review> reviewslist=service.getAllreviews(companyid);
        return reviewslist.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }
}
