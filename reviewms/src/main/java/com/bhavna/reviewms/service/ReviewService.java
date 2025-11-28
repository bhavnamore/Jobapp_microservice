package com.bhavna.reviewms.service;
import com.bhavna.reviewms.messaging.ReviewMessageProducer;
import com.bhavna.reviewms.model.Review;
import com.bhavna.reviewms.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService
{
    @Autowired
    private ReviewRepository repo;

    @Autowired
    private ReviewMessageProducer reviewmessageproducer;

    public List<Review> getAllreviews(Long id)
    {
        List<Review> reviews=repo.findAll();
        List<Review> compreviews=new ArrayList<>();
        for(Review review:reviews)
        {
            if(review.getCompanyId()==id)
            {
                compreviews.add(review);
            }
        }
        return compreviews;
    }

    public boolean takeReview(Long companyId,Review review)
    {
        if(companyId!=null)
        {
            reviewmessageproducer.sendMessage(review);
            repo.save(review);
            return true;
        }
        return false;
    }

    public boolean updReview(Long compid,int rev_id,Review review)
    {
        Optional<Review> optional=repo.findByIdAndCompanyId(rev_id,compid);
        if(optional.isPresent())
        {
            Review existing=optional.get();
            existing.setTitle(review.getTitle());
            existing.setDescription(review.getDescription());
            existing.setRating(review.getRating());

            repo.save(existing);
            return true;
        }
        return false;
    }

    public boolean delReview(Long compid,int rev_id)
    {
        Optional<Review> optional=repo.findByIdAndCompanyId(rev_id,compid);
        if(optional.isPresent())
        {
            Review review=optional.get();
            repo.delete(review);
            return true;
        }
        return false;
    }

}
