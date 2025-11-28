package com.bhavna.reviewms.messaging;


import com.bhavna.reviewms.dto.ReviewMessage;
import com.bhavna.reviewms.model.Review;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageProducer
{
    private final RabbitTemplate rabbittemplate;
    public ReviewMessageProducer(RabbitTemplate rabbittemplate)
    {
        this.rabbittemplate=rabbittemplate;
    }
    public void sendMessage(Review review)
    {
        ReviewMessage reviewmessage=new ReviewMessage();
        reviewmessage.setId(review.getId());
        reviewmessage.setDescription(review.getDescription());
        reviewmessage.setTitle(review.getTitle());
        reviewmessage.setRating(review.getRating());
        reviewmessage.setCompanyId(review.getCompanyId());
        rabbittemplate.convertAndSend("companyRatingQueue",reviewmessage);
    }
}
